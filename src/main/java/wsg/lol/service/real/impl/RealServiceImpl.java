package wsg.lol.service.real.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import wsg.lol.common.utils.LogUtil;
import wsg.lol.dao.api.impl.LeagueV4;
import wsg.lol.dao.api.impl.MatchV4;
import wsg.lol.dao.api.impl.SummonerV4;
import wsg.lol.dao.mybatis.mapper.MatchMapper;
import wsg.lol.dao.mybatis.mapper.ParticipantMapper;
import wsg.lol.dao.mybatis.mapper.PositionMapper;
import wsg.lol.dao.mybatis.mapper.SummonerMapper;
import wsg.lol.pojo.base.BaseResult;
import wsg.lol.pojo.base.Page;
import wsg.lol.pojo.dmo.league.ItemDmo;
import wsg.lol.pojo.dmo.league.PositionDmo;
import wsg.lol.pojo.dmo.match.MatchDmo;
import wsg.lol.pojo.dmo.match.ParticipantDmo;
import wsg.lol.pojo.dmo.summoner.SummonerDmo;
import wsg.lol.pojo.dto.api.league.LeagueExtDto;
import wsg.lol.pojo.dto.api.match.MatchDto;
import wsg.lol.pojo.dto.api.match.MatchListDto;
import wsg.lol.pojo.dto.api.match.QueryMatchListDto;
import wsg.lol.pojo.dto.query.GetSummonerDto;
import wsg.lol.pojo.enums.impl.code.TierEnum;
import wsg.lol.pojo.enums.impl.others.DivisionEnum;
import wsg.lol.pojo.enums.impl.others.PositionEnum;
import wsg.lol.pojo.enums.impl.others.RankQueueEnum;
import wsg.lol.pojo.exception.AppException;
import wsg.lol.service.real.intf.RealService;

import java.util.*;

/**
 * wsg
 *
 * @author wangsigen
 */
@Service("realAction")
public class RealServiceImpl implements RealService {

    private SummonerMapper summonerMapper;

    private MatchMapper matchMapper;

    private PositionMapper positionMapper;

    private MatchV4 matchV4;

    private LeagueV4 leagueV4;

    private SummonerV4 summonerV4;

    private ParticipantMapper participantMapper;

    private TransactionTemplate transactionTemplate;

    @Override
    public BaseResult extendSummonerLibByMatch() {
        // Get last unchecked summoners.
        List<SummonerDmo> summonerBaseList = summonerMapper.queryLastUncheckedSummoners(new Page());

        for (SummonerDmo summonerDmo : summonerBaseList) {
            QueryMatchListDto queryMatchListDto = new QueryMatchListDto();
            queryMatchListDto.setBeginTime(summonerDmo.getLastCheckedTime().getTime());
            for (long beginIndex = 0; ; beginIndex += QueryMatchListDto.MAX_INDEX_RANGE) {
                // query match list.
                queryMatchListDto.setBeginIndex(beginIndex);
                Date now = new Date();
                MatchListDto matchListDto = matchV4.getMatchListByAccount(summonerDmo.getAccountId(),
                        queryMatchListDto);
                List<MatchDmo> matchDmoList = matchListDto.getMatches();
                if (matchDmoList.isEmpty()) {
                    break;
                }

                Set<Long> gameIdSet = new HashSet<>();
                for (MatchDmo matchDmo : matchDmoList) {
                    gameIdSet.add(matchDmo.getGameId());
                }

                BaseResult result = saveMatches(gameIdSet);
                if (result.isSuccess()) {
                    summonerDmo.setLastCheckedTime(now);
                    if (1 != summonerMapper.updateLastCheckedTime(summonerDmo)) {
                        LogUtil.info("Fail to update last checked time of summoner.");
                    }
                }
            }
        }

        return BaseResult.success();
    }

    @Override
    public BaseResult updateLeagues() {
        return BaseResult.success();
    }

    @Override
    public BaseResult buildBaseSummonerLib() {
        return buildApexSummonerLib();
    }

    @Override
    public BaseResult buildApexSummonerLib() {
        Set<String> summonerIdSet = new HashSet<>();

        // get apex summoners
        for (TierEnum tier : TierEnum.apexValues()) {
            for (RankQueueEnum queue : RankQueueEnum.values()) {
                LeagueExtDto leagueExtDto = leagueV4.getApexLeagueByQueue(queue, tier);
                for (ItemDmo itemDmo : leagueExtDto.getItemDmoList()) {
                    summonerIdSet.add(itemDmo.getSummonerId());
                }
            }
        }

        return saveSummoners(summonerIdSet);
    }

    @Override
    public BaseResult updateSummonerData(SummonerDmo summonerDmo) {
        List<PositionDmo> positionDmoList = leagueV4.getLeaguePositionsBySummonerId(summonerDmo.getId());
        positionMapper.batchInsertPosition(positionDmoList);
        for (PositionDmo positionDmo : positionDmoList) {
            // wsg
        }

        return BaseResult.success();
    }

    @Override
    public BaseResult buildPositionalSummonerLib(TierEnum tier, DivisionEnum division, PositionEnum position) {
        Set<String> summonerIdSet = new HashSet<>();
        for (RankQueueEnum queue : RankQueueEnum.positionalValues()) {
            for (int i = 0; ; i++) {
                List<PositionDmo> positionDmoList = leagueV4.getAllPositionLeagues(queue, tier, division,
                        position, i);
                if (positionDmoList == null || positionDmoList.isEmpty())
                    break;
                for (PositionDmo positionDmo : positionDmoList) {
                    summonerIdSet.add(positionDmo.getSummonerId());
                }
            }
        }

        return saveSummoners(summonerIdSet);
    }

    private BaseResult saveSummoners(Set<String> summonerIdSet) {
        if (summonerIdSet.isEmpty()) {
            return BaseResult.success();
        }
        List<String> idUncheckedList = summonerMapper.checkSummonersNotExist(new ArrayList<>(summonerIdSet));

        LogUtil.info("Save summoners: " + idUncheckedList.size());
        GetSummonerDto getSummonerDto = new GetSummonerDto();
        for (String id : idUncheckedList) {
            getSummonerDto.setId(id);
            SummonerDmo summonerDmo = summonerV4.getSummoner(getSummonerDto);
            if (1 != summonerMapper.insertSummoner(summonerDmo)) {
                LogUtil.info("Fail to insert summoner.");
            }
        }
        return BaseResult.success();
    }

    private BaseResult saveMatches(Set<Long> gameIdSet) {
        if (gameIdSet.isEmpty()) {
            return BaseResult.success();
        }
        List<Long> idsNotExist = matchMapper.checkMatchesNotExist(new ArrayList<>(gameIdSet));

        LogUtil.info("Save matches: " + idsNotExist.size());
        for (Long id : idsNotExist) {
            MatchDto matchDto = matchV4.getMatchById(id);

            // save summoners.
            Set<String> summonerIdSet = new HashSet<>();
            for (ParticipantDmo participantDmo : matchDto.getParticipantDmoList()) {
                summonerIdSet.add(participantDmo.getSummonerId());
            }
            saveSummoners(summonerIdSet);

            // save matches.
            transactionTemplate.execute(transactionStatus -> {
                if (1 != matchMapper.insertSelective(matchDto.getMatchDmo())) {
                    LogUtil.info("Fail to insert matchDmo");
                    return BaseResult.fail();
                }
                List<ParticipantDmo> list = matchDto.getParticipantDmoList();
                if (list.size() != participantMapper.batchInsertParticipants(list)) {
                    throw new AppException("Fail to batch insert participants of the match.");
                }
                return BaseResult.success();
            });
        }

        return BaseResult.success();
    }

    @Autowired
    public void setSummonerMapper(SummonerMapper summonerMapper) {
        this.summonerMapper = summonerMapper;
    }

    @Autowired
    public void setMatchMapper(MatchMapper matchMapper) {
        this.matchMapper = matchMapper;
    }

    @Autowired
    public void setPositionMapper(PositionMapper positionMapper) {
        this.positionMapper = positionMapper;
    }

    @Autowired
    public void setMatchV4(MatchV4 matchV4) {
        this.matchV4 = matchV4;
    }

    @Autowired
    public void setLeagueV4(LeagueV4 leagueV4) {
        this.leagueV4 = leagueV4;
    }

    @Autowired
    public void setSummonerV4(SummonerV4 summonerV4) {
        this.summonerV4 = summonerV4;
    }

    @Autowired
    public void setParticipantMapper(ParticipantMapper participantMapper) {
        this.participantMapper = participantMapper;
    }

    @Autowired
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }
}
