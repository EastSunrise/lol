package wsg.lol.service.scheduler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wsg.lol.common.base.BaseResult;
import wsg.lol.common.base.Page;
import wsg.lol.common.enums.impl.name.TierEnum;
import wsg.lol.common.enums.impl.others.DivisionEnum;
import wsg.lol.common.enums.impl.others.PositionEnum;
import wsg.lol.common.enums.impl.others.RankQueueEnum;
import wsg.lol.common.utils.LogUtil;
import wsg.lol.dao.mapper.MatchMapper;
import wsg.lol.dao.mapper.SummonerMapper;
import wsg.lol.data.api.LeagueV4;
import wsg.lol.data.api.MatchV4;
import wsg.lol.data.api.SummonerV4;
import wsg.lol.dmo.league.ItemDmo;
import wsg.lol.dmo.league.PositionDmo;
import wsg.lol.dmo.match.MatchDmo;
import wsg.lol.dmo.match.ParticipantDmo;
import wsg.lol.dmo.summoner.SummonerDmo;
import wsg.lol.dto.api.league.LeagueExtDto;
import wsg.lol.dto.api.match.MatchDto;
import wsg.lol.dto.api.match.MatchListDto;
import wsg.lol.dto.api.match.QueryMatchListDto;
import wsg.lol.dto.query.GetSummonerDto;
import wsg.lol.service.scheduler.intf.RealAction;

import java.util.*;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-02-28 14:47
 */
@Service("realAction")
public class RealActionImpl implements RealAction {

    @Autowired
    private SummonerMapper summonerMapper;

    @Autowired
    private MatchMapper matchMapper;

    public static final int BASE_COUNT = 20;

    @Override
    public BaseResult buildBaseSummonerLibByLeague() {
        buildApexSummonerLib();

        for (TierEnum tier : TierEnum.positionalValues()) {
            for (DivisionEnum division : DivisionEnum.values()) {
                for (PositionEnum position : PositionEnum.positionalValues()) {
                    buildPositionalSummonerLib(tier, division, position);
                }
            }
        }

        return BaseResult.success();
    }

    @Override
    public BaseResult buildApexSummonerLib() {
        Set<String> summonerIdSet = new HashSet<>();

        // get apex summoners
        for (TierEnum tier : TierEnum.apexValues()) {
            for (RankQueueEnum queue : RankQueueEnum.values()) {
                LeagueExtDto leagueExtDto = LeagueV4.getApexLeagueByQueue(queue, tier);
                for (ItemDmo itemDmo : leagueExtDto.getItemDmoList()) {
                    summonerIdSet.add(itemDmo.getSummonerId());
                }
            }
        }

        return saveSummoners(summonerIdSet);
    }

    @Override
    public BaseResult buildPositionalSummonerLib(TierEnum tier, DivisionEnum division, PositionEnum position) {
        Set<String> summonerIdSet = new HashSet<>();
        for (RankQueueEnum queue : RankQueueEnum.positionalValues()) {
            for (int i = 0; ; i++) {
                List<PositionDmo> positionDmoList = LeagueV4.getAllPositionLeagues(queue, tier, division,
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

    @Override
    public BaseResult extendSummonerLibByMatch() {
        // Get last unchecked summoners.
        List<SummonerDmo> summonerBaseList = summonerMapper.queryLastUncheckedSummoners(new Page());

        // Get the set of matchIds by accounts of summoners.
        Set<Long> gameIdSet = new HashSet<>();
        for (SummonerDmo summonerDmo : summonerBaseList) {
            QueryMatchListDto queryMatchListDto = new QueryMatchListDto();
            long beginTime = summonerDmo.getLastCheckedTime().getTime();
            queryMatchListDto.setBeginTime(beginTime);
            for (long beginIndex = 0; ; beginIndex += QueryMatchListDto.MAX_INDEX_RANGE) {
                queryMatchListDto.setBeginIndex(beginIndex);
                MatchListDto matchListDto = MatchV4.getMatchListByAccount(summonerDmo.getAccountId(),
                        queryMatchListDto);
                List<MatchDmo> matchDmoList = matchListDto.getMatches();
                if (matchDmoList.isEmpty())
                    break;
                for (MatchDmo match : matchDmoList) {
                    gameIdSet.add(match.getGameId());
                }
            }
        }

        // Get all summoners of these matches.
        Set<String> summonerIdSet = new HashSet<>();
        List<MatchDto> matchDtoList = new LinkedList<>();
        for (Long gameId : gameIdSet) {
            MatchDto matchDto = MatchV4.getMatchById(gameId);
            for (ParticipantDmo participantDmo : matchDto.getParticipantDmoList()) {
                summonerIdSet.add(participantDmo.getSummonerId());
            }
            matchDtoList.add(matchDto);
        }

        // Add info of summoners if not exist.
        saveSummoners(summonerIdSet);

        // Add info of matches if not exist.
        matchMapper.batchInsertMatchIfNotExist(matchDtoList);

        return BaseResult.success();
    }


    private BaseResult saveSummoners(Set<String> summonerIdSet) {
        List<String> idUncheckedList = summonerMapper.checkSummonersNotExist(new ArrayList<>(summonerIdSet));
        LogUtil.info("Query summoners: " + idUncheckedList.size());
        List<SummonerDmo> summonerDmoList = new LinkedList<>();
        int count = 0;
        GetSummonerDto getSummonerDto = new GetSummonerDto();
        for (String id : idUncheckedList) {
            getSummonerDto.setId(id);
            summonerDmoList.add(SummonerV4.getSummoner(getSummonerDto));
            if (++count % BASE_COUNT == 0) {
                if (summonerDmoList.size() != summonerMapper.batchInsertSummoner(summonerDmoList)) {
                    LogUtil.info("Fail to batch insert summoners.");
                }
                summonerDmoList = new LinkedList<>();
            }
        }
        if (summonerDmoList.size() > 0) {
            if (summonerDmoList.size() != summonerMapper.batchInsertSummoner(summonerDmoList)) {
                LogUtil.info("Fail to batch insert summoners.");
            }
        }

        return BaseResult.success();
    }
}
