package wsg.lol.service.real.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import wsg.lol.dao.api.impl.ChampionMasteryV4;
import wsg.lol.dao.api.impl.LeagueV4;
import wsg.lol.dao.api.impl.MatchV4;
import wsg.lol.dao.api.impl.SummonerV4;
import wsg.lol.dao.mongo.intf.MongoDao;
import wsg.lol.dao.mybatis.mapper.*;
import wsg.lol.pojo.base.BaseResult;
import wsg.lol.pojo.base.Page;
import wsg.lol.pojo.dmo.champion.ChampionMasteryDmo;
import wsg.lol.pojo.dmo.league.PositionDmo;
import wsg.lol.pojo.dmo.match.MatchReferenceDmo;
import wsg.lol.pojo.dmo.summoner.SummonerDmo;
import wsg.lol.pojo.dto.api.champion.ChampionMasteryDto;
import wsg.lol.pojo.dto.api.league.LeagueItemDto;
import wsg.lol.pojo.dto.api.league.LeagueListDto;
import wsg.lol.pojo.dto.api.league.LeaguePositionDto;
import wsg.lol.pojo.dto.api.match.MatchDto;
import wsg.lol.pojo.dto.api.match.MatchListDto;
import wsg.lol.pojo.dto.api.match.MatchReferenceDto;
import wsg.lol.pojo.dto.api.query.QueryMatchListDto;
import wsg.lol.pojo.dto.api.summoner.SummonerDto;
import wsg.lol.pojo.dto.query.GetSummonerDto;
import wsg.lol.pojo.enums.impl.code.DivisionEnum;
import wsg.lol.pojo.enums.impl.code.PositionEnum;
import wsg.lol.pojo.enums.impl.code.RankQueueEnum;
import wsg.lol.pojo.enums.impl.code.TierEnum;
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

    private static Logger logger = LoggerFactory.getLogger(RealService.class);

    private SummonerMapper summonerMapper;

    private PositionMapper positionMapper;

    private MatchMapper matchMapper;

    private MasteryMapper masteryMapper;

    private ChampionMasteryV4 championMasteryV4;

    private MatchV4 matchV4;

    private LeagueV4 leagueV4;

    private SummonerV4 summonerV4;

    private MongoDao mongoDao;

    private ParticipantMapper participantMapper;

    private TransactionTemplate transactionTemplate;

    @Override
    public BaseResult buildBaseLib() {
        return buildApexSummonerLib();
    }

    @Override
    public BaseResult buildApexSummonerLib() {
        Set<String> summonerIdSet = new HashSet<>();

        // get apex summoners
        for (TierEnum tier : TierEnum.apexValues()) {
            for (RankQueueEnum queue : RankQueueEnum.values()) {
                LeagueListDto leagueListDto = leagueV4.getApexLeagueByQueue(queue, tier);
                for (LeagueItemDto leagueItemDto : leagueListDto.getItemDmoList()) {
                    summonerIdSet.add(leagueItemDto.getSummonerId());
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

    @Override
    public BaseResult updateSummoners() {
        // Get last unchecked summoners.
        logger.info("Get last unchecked summoners");
        List<SummonerDmo> summonerBaseList = summonerMapper.queryLastUncheckedSummoners(new Page());
        for (SummonerDmo summonerDmo : summonerBaseList) {
            updateSummonerById(summonerDmo.getId());
        }

        return BaseResult.success();
    }

    @Override
    @Transactional
    public BaseResult updateSummonerById(String summonerId) {
        logger.info("Start to update " + summonerId);
        // update base info.
        SummonerDmo summonerDmo = summonerMapper.selectByPrimaryKey(summonerId);
        if (summonerDmo == null) {
            GetSummonerDto getSummonerDto = new GetSummonerDto();
            getSummonerDto.setId(summonerId);
            SummonerDto summonerDto = summonerV4.getSummoner(getSummonerDto);
            if (1 != summonerMapper.insertSummoner(summonerDto)) {
                throw new AppException("Fail to insert summoner.");
            }
            summonerDmo = summonerMapper.selectByPrimaryKey(summonerId);
        }

        // update the league.
        List<LeaguePositionDto> positionDtoList = leagueV4.getLeaguePositionsBySummonerId(summonerId);
        for (LeaguePositionDto positionDto : positionDtoList) {
            PositionDmo positionDmo = positionMapper.selectByUnionKey(positionDto.getSummonerId(),
                    positionDto.getQueueType(),
                    positionDto.getPosition());
            if (positionDmo == null) {
                if (1 != positionMapper.insert(positionDto)) {
                    throw new AppException("Fail to insert position.");
                }
            } else {
                if (1 != positionMapper.updateByUnionKey(positionDto)) {
                    throw new AppException("Fail to update position.");
                }
            }
        }

        // update the mastery.
        List<ChampionMasteryDto> masteryDtoList = championMasteryV4.getChampionMasteryBySummonerId(summonerId);
        for (ChampionMasteryDto masteryDto : masteryDtoList) {
            ChampionMasteryDmo masteryDmo = masteryMapper.selectByUnionKey(masteryDto.getSummonerId(),
                    masteryDto.getChampionId());
            if (masteryDmo == null) {
                if (1 != masteryMapper.insert(masteryDto)) {
                    throw new AppException("Fail to insert champion mastery.");
                }
            } else {
                if (1 != masteryMapper.updateByUnionKey(masteryDto)) {
                    throw new AppException("Fail to update champion mastery.");
                }
            }
        }

        Date lastCheckedTime = summonerDmo.getLastCheckedTime();
        QueryMatchListDto queryMatchListDto = new QueryMatchListDto();
        queryMatchListDto.setBeginTime(lastCheckedTime.getTime());
        for (long beginIndex = 0; ; beginIndex += QueryMatchListDto.MAX_INDEX_RANGE) {
            queryMatchListDto.setBeginIndex(beginIndex);
            lastCheckedTime = new Date();
            MatchListDto matchListDto = matchV4.getMatchListByAccount(summonerDmo.getAccountId(), queryMatchListDto);
            if (matchListDto == null) {
                break;
            }
            List<MatchReferenceDto> referenceDtoList = matchListDto.getMatches();
            if (referenceDtoList.isEmpty()) {
                break;
            }
            for (MatchReferenceDto referenceDto : referenceDtoList) {
                referenceDto.setSummonerId(summonerId);
                try {
                    if (1 != matchMapper.insert(referenceDto)) {
                        throw new AppException("Fail to insert match reference.");
                    }
                } catch (DuplicateKeyException e) {
                    e.printStackTrace();
                }
            }
        }

        // update the last check time.
        if (1 != summonerMapper.updateLastCheckedTimeById(summonerId, lastCheckedTime)) {
            throw new AppException("Fail to update the last check time of the summoner.");
        }

        return BaseResult.success();
    }

    @Override
    public BaseResult extendLib() {
        logger.info("Get last unchecked matches.");
        List<MatchReferenceDmo> referenceDmoList = matchMapper.queryLastUncheckedMatches(new Page());

        for (MatchReferenceDmo referenceDmo : referenceDmoList) {
            MatchDto matchDto = mongoDao.getCollectionById(referenceDmo.getGameId(), MatchDto.class);
            if (matchDto == null) {
                matchDto = matchV4.getMatchById(referenceDmo.getGameId());
                // wsg
            }
        }
        return BaseResult.success();
    }

    private BaseResult saveSummoners(Set<String> summonerIdSet) {
        if (summonerIdSet.isEmpty()) {
            return BaseResult.success();
        }
        List<String> idUncheckedList = summonerMapper.checkSummonersNotExist(new ArrayList<>(summonerIdSet));

        logger.info("Save summoners: " + idUncheckedList.size());
        GetSummonerDto getSummonerDto = new GetSummonerDto();
        for (String id : idUncheckedList) {
            getSummonerDto.setId(id);
            SummonerDto summonerDto = summonerV4.getSummoner(getSummonerDto);
            if (1 != summonerMapper.insertSummoner(summonerDto)) {
                logger.error("Fail to insert summoner.");
            }
        }
        return BaseResult.success();
    }

    @Autowired
    public void setPositionMapper(PositionMapper positionMapper) {
        this.positionMapper = positionMapper;
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
    public void setChampionMasteryV4(ChampionMasteryV4 championMasteryV4) {
        this.championMasteryV4 = championMasteryV4;
    }

    @Autowired
    public void setMasteryMapper(MasteryMapper masteryMapper) {
        this.masteryMapper = masteryMapper;
    }

    @Autowired
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    @Autowired
    public void setMongoDao(MongoDao mongoDao) {
        this.mongoDao = mongoDao;
    }
}
