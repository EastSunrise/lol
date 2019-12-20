package wsg.lol.service.impl;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import wsg.lol.common.annotation.AssignApi;
import wsg.lol.common.base.*;
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
import wsg.lol.common.util.ResultUtils;
import wsg.lol.config.RegionIdentifier;
import wsg.lol.dao.api.impl.ChampionMasteryV4;
import wsg.lol.dao.api.impl.LeagueV4;
import wsg.lol.dao.api.impl.MatchV4;
import wsg.lol.dao.api.impl.SummonerV4;
import wsg.lol.dao.mybatis.mapper.region.summoner.ChampionMasteryMapper;
import wsg.lol.dao.mybatis.mapper.region.summoner.LeagueEntryMapper;
import wsg.lol.dao.mybatis.mapper.region.summoner.SummonerMapper;
import wsg.lol.service.common.PriorityUtils;
import wsg.lol.service.common.ServiceExecutor;
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
public class SummonerServiceImpl implements SummonerService {

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
    @AssignApi(encryptUsername = "#encryptUsername")
    public Result updateSummoner(String summonerId, String encryptUsername) {
        try {
            return transactionTemplate.execute(transactionStatus -> {
                logger.info("Updating the summoner {}...", summonerId);

                logger.info("Updating champion masteries of {}...", summonerId);
                List<ChampionMasteryDto> championMasteries = championMasteryV4.getChampionMasteryBySummonerId(summonerId);
                ServiceExecutor.replaceList(championMasteryMapper, ObjectTransfer.transferDtoList(championMasteries, ChampionMasteryDo.class)).assertSuccess();

                logger.info("Updating league entries of {}...", summonerId);
                List<LeagueEntryDto> entries = leagueV4.getLeagueEntriesBySummonerId(summonerId);
                ServiceExecutor.replaceList(leagueEntryMapper, ObjectTransfer.transferDtoList(entries, LeagueEntryDo.class)).assertSuccess();

                SummonerDto summoner = summonerV4.getSummonerById(summonerId);
                SummonerDo summonerDo = ObjectTransfer.transferDto(summoner, SummonerDo.class);
                int score = championMasteryV4.getScoreBySummonerId(summonerId);
                summonerDo.setScore(score);
                summonerDo.setLastUpdate(new Date());
                int count = summonerMapper.updateByPrimaryKeySelective(summonerDo);
                if (count != 1) {
                    throw new AppException(ErrorCodeConst.DATABASE_ERROR, "Failed to update the summoner " + summonerId).error(logger);
                }

                logger.info("Updated the summoner {}", summonerId);
                return ResultUtils.success();
            });
        } catch (ApiHTTPException e) {
            logger.error("Failed to update summoner {}.", summonerId);
            logger.error("{}: {}", e.getResponseCode().getMessage(), e.getUrl());
        } catch (Exception e) {
            logger.error("Failed to update summoner {}.", summonerId);
            logger.error(e.getMessage(), e);
        }

        logger.info("Updating the last time updating the summoner {}.", summonerId);
        int count = summonerMapper.updateLastUpdate(summonerId, ConfigConst.LAST_UPDATE_ERROR_DATE);
        if (count != 1) {
            return ResultUtils.fail(ErrorCodeConst.DATABASE_ERROR, "Failed to update the last time updating the summoner " + summonerId).error(logger);
        }
        return ResultUtils.success();
    }

    @Override
    @AssignApi(encryptUsername = "#encryptUsername")
    public Result updateMatches(String accountId, Date beginTime, String encryptUsername) {
        try {
            return transactionTemplate.execute(transactionStatus -> {
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

                eventService.insertEvents(EventTypeEnum.Match, map).assertSuccess();
                updateSummonerLastMatch(accountId, lastMatch).assertSuccess();

                logger.info("Succeed in adding events of matches of the account {}.", accountId);
                return ResultUtils.success();
            });
        } catch (ApiHTTPException e) {
            logger.error("Failed to update matches of the account {}.", accountId);
            logger.error("{}: {}", e.getResponseCode().getMessage(), e.getUrl());
        } catch (Exception e) {
            logger.error("Failed to update matches of the account {}.", accountId);
            logger.error(e.getMessage(), e);
        }

        return updateSummonerLastMatch(accountId, ConfigConst.LAST_MATCH_ERROR_DATE).error(logger);
    }

    @Override
    public ListResult<SummonerDto> getSummonersForUpdate(RowBounds rowBounds) {
        List<SummonerDo> summoners = summonerMapper.selectByExampleAndRowBounds(PriorityUtils.updateSummoners(), rowBounds);
        List<SummonerDto> summonerDtoList = ObjectTransfer.transferDoList(summoners, SummonerDto.class);
        return ListResult.create(summonerDtoList);
    }

    @Override
    public ListResult<SummonerDto> getSummonersForMatch(RowBounds rowBounds) {
        List<SummonerDo> summoners = summonerMapper.selectByExampleAndRowBounds(PriorityUtils.updateMatches(), rowBounds);
        List<SummonerDto> summonerDtoList = ObjectTransfer.transferDoList(summoners, SummonerDto.class);
        return ListResult.create(summonerDtoList);
    }

    @Override
    public GenericResult<SummonerDto> getSummonersByName(String summonerName) {
        SummonerDo cond = new SummonerDo();
        cond.setName(summonerName);
        SummonerDo summonerDo = summonerMapper.selectOne(cond);
        SummonerDto summonerDto = ObjectTransfer.transferDo(summonerDo, SummonerDto.class);
        return GenericResult.create(summonerDto);
    }

    @Override
    public GenericResult<SummonerDto> getSummonersById(String summonerId) {
        SummonerDo cond = new SummonerDo();
        cond.setId(summonerId);
        SummonerDo summonerDo = summonerMapper.selectOne(cond);
        SummonerDto summonerDto = ObjectTransfer.transferDo(summonerDo, SummonerDto.class);
        return GenericResult.create(summonerDto);
    }

    private Result updateSummonerLastMatch(String accountId, Date lastMatch) {
        logger.info("Updating the last match of the account {}.", accountId);
        int count = summonerMapper.updateLastMatch(accountId, lastMatch);
        if (count != 1) {
            logger.error("Failed to update the last match of the account {}.", accountId);
            return ResultUtils.fail(ErrorCodeConst.DATABASE_ERROR, "Failed to update the last match of the account " + accountId);
        }
        return ResultUtils.success();
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
