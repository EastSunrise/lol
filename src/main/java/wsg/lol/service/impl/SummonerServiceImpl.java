package wsg.lol.service.impl;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import wsg.lol.common.base.ApiHTTPException;
import wsg.lol.common.base.AppException;
import wsg.lol.common.constant.ConfigConst;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.common.enums.system.EventTypeEnum;
import wsg.lol.common.enums.system.RegionEnum;
import wsg.lol.common.pojo.domain.summoner.ChampionMasteryDo;
import wsg.lol.common.pojo.domain.summoner.LeagueEntryDo;
import wsg.lol.common.pojo.domain.summoner.SummonerDo;
import wsg.lol.common.pojo.dto.match.MatchListDto;
import wsg.lol.common.pojo.dto.match.MatchReferenceDto;
import wsg.lol.common.pojo.dto.summoner.ChampionMasteryDto;
import wsg.lol.common.pojo.dto.summoner.LeagueEntryDto;
import wsg.lol.common.pojo.dto.summoner.SummonerDto;
import wsg.lol.common.pojo.query.QueryMatchListDto;
import wsg.lol.common.pojo.transfer.ObjectTransfer;
import wsg.lol.config.RegionIdentifier;
import wsg.lol.dao.api.impl.ChampionMasteryV4;
import wsg.lol.dao.api.impl.LeagueV4;
import wsg.lol.dao.api.impl.MatchV4;
import wsg.lol.dao.api.impl.SummonerV4;
import wsg.lol.dao.mybatis.mapper.region.summoner.ChampionMasteryMapper;
import wsg.lol.dao.mybatis.mapper.region.summoner.LeagueEntryMapper;
import wsg.lol.dao.mybatis.mapper.region.summoner.SummonerMapper;
import wsg.lol.service.base.BaseService;
import wsg.lol.service.common.PriorityUtils;
import wsg.lol.service.intf.EventService;
import wsg.lol.service.intf.SummonerService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kingen
 */
@Service
public class SummonerServiceImpl extends BaseService implements SummonerService {

    private static final Logger logger = LoggerFactory.getLogger(SummonerService.class);

    private SummonerV4 summonerV4;

    private LeagueV4 leagueV4;

    private ChampionMasteryV4 championMasteryV4;

    private MatchV4 matchV4;

    private SummonerMapper summonerMapper;

    private ChampionMasteryMapper championMasteryMapper;

    private LeagueEntryMapper leagueEntryMapper;

    private TransactionTemplate transactionTemplate;

    private EventService eventService;

    @Override
    public void updateSummoner(String summonerId) {
        try {
            transactionTemplate.execute(transactionStatus -> {
                logger.info("Updating the summoner {}...", summonerId);

                logger.info("Updating champion masteries of {}...", summonerId);
                List<ChampionMasteryDto> championMasteries = championMasteryV4.getChampionMasteryBySummonerId(summonerId);
                replaceList(championMasteryMapper, ObjectTransfer.transferDtoList(championMasteries, ChampionMasteryDo.class));

                logger.info("Updating league entries of {}...", summonerId);
                List<LeagueEntryDto> entries = leagueV4.getLeagueEntriesBySummonerId(summonerId);
                replaceList(leagueEntryMapper, ObjectTransfer.transferDtoList(entries, LeagueEntryDo.class));

                SummonerDto summoner = summonerV4.getSummonerById(summonerId);
                SummonerDo summonerDo = ObjectTransfer.transferDto(summoner, SummonerDo.class);
                int score = championMasteryV4.getScoreBySummonerId(summonerId);
                summonerDo.setScore(score);
                summonerDo.setLastUpdate(new Date());
                updateByPrimaryKeySelective(summonerMapper, summonerDo);

                return null;
            });
        } catch (ApiHTTPException e) {
            logger.error("Failed to update summoner {}.", summonerId);
            logger.error("{}: {}", e.getResponseCode().getMessage(), e.getUrl());
            logger.info("Updating the last time updating the summoner {}.", summonerId);
            int count = summonerMapper.updateLastUpdate(summonerId, ConfigConst.LAST_UPDATE_ERROR_DATE);
            if (count != 1) {
                throw new AppException(ErrorCodeConst.DATABASE_ERROR, "Failed to update the last time updating the summoner " + summonerId).error(logger);
            }
        }
    }

    @Override
    public void updateMatches(String accountId, Date beginTime) {
        try {
            transactionTemplate.execute(transactionStatus -> {
                logger.info("Adding events of matches of the account {}...", accountId);
                Map<String, String> map = new HashMap<>();
                QueryMatchListDto queryMatchListDto = new QueryMatchListDto();
                queryMatchListDto.setBeginTime(beginTime.getTime());
                long beginIndex = 0L, total;
                Date lastMatch;
                RegionEnum region = RegionIdentifier.getRegion();

                do {
                    queryMatchListDto.setBeginIndex(beginIndex);
                    lastMatch = new Date();
                    MatchListDto matchListDto = matchV4.getMatchListByAccount(accountId, queryMatchListDto);
                    for (MatchReferenceDto match : matchListDto.getMatches()) {
                        if (region.equals(match.getPlatformId())) {
                            map.put(match.getGameId().toString(), accountId);
                        }
                    }
                    total = matchListDto.getTotalGames();
                    beginIndex = matchListDto.getEndIndex();
                } while (beginIndex < total);

                eventService.insertEvents(EventTypeEnum.Match, map);
                updateSummonerLastMatch(accountId, lastMatch);

                logger.info("Succeed in adding events of matches of the account {}.", accountId);

                return null;
            });
        } catch (ApiHTTPException e) {
            logger.error("Failed to update matches of the account {}.", accountId);
            logger.error("{}: {}", e.getResponseCode().getMessage(), e.getUrl());
            updateSummonerLastMatch(accountId, ConfigConst.LAST_MATCH_ERROR_DATE);
        }
    }

    @Override
    public List<SummonerDto> getSummonersForUpdate(RowBounds rowBounds) {
        List<SummonerDo> summoners = selectByExampleAndRowBounds(summonerMapper, PriorityUtils.updateSummoners(), rowBounds);
        return ObjectTransfer.transferDoList(summoners, SummonerDto.class);
    }

    @Override
    public List<SummonerDto> getSummonersForMatch(RowBounds rowBounds) {
        List<SummonerDo> summoners = selectByExampleAndRowBounds(summonerMapper, PriorityUtils.updateMatches(), rowBounds);
        return ObjectTransfer.transferDoList(summoners, SummonerDto.class);
    }

    @Override
    public SummonerDto getSummonersByName(String summonerName) {
        SummonerDo cond = new SummonerDo();
        cond.setName(summonerName);
        return selectOne(summonerMapper, cond, SummonerDto.class);
    }

    @Override
    public SummonerDto getSummonersById(String summonerId) {
        SummonerDo cond = new SummonerDo();
        cond.setId(summonerId);
        return selectOne(summonerMapper, cond, SummonerDto.class);
    }

    private void updateSummonerLastMatch(String accountId, Date lastMatch) {
        logger.info("Updating the last match of the account {}.", accountId);
        int count = summonerMapper.updateLastMatch(accountId, lastMatch);
        if (count != 1) {
            throw new AppException(ErrorCodeConst.DATABASE_ERROR, "Failed to update the last match of the account " + accountId).error(logger);
        }
    }

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    @Autowired
    public void setMatchV4(MatchV4 matchV4) {
        this.matchV4 = matchV4;
    }

    @Autowired
    public void setLeagueEntryMapper(LeagueEntryMapper leagueEntryMapper) {
        this.leagueEntryMapper = leagueEntryMapper;
    }

    @Autowired
    public void setLeagueV4(LeagueV4 leagueV4) {
        this.leagueV4 = leagueV4;
    }

    @Autowired
    public void setChampionMasteryMapper(ChampionMasteryMapper championMasteryMapper) {
        this.championMasteryMapper = championMasteryMapper;
    }

    @Autowired
    public void setSummonerV4(SummonerV4 summonerV4) {
        this.summonerV4 = summonerV4;
    }

    @Autowired
    public void setChampionMasteryV4(ChampionMasteryV4 championMasteryV4) {
        this.championMasteryV4 = championMasteryV4;
    }

    @Autowired
    public void setSummonerMapper(SummonerMapper summonerMapper) {
        this.summonerMapper = summonerMapper;
    }
}
