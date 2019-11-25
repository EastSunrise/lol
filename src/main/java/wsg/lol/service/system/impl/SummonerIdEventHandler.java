package wsg.lol.service.system.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import wsg.lol.common.base.AppException;
import wsg.lol.common.base.Result;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.common.enums.system.EventStatusEnum;
import wsg.lol.common.enums.system.EventTypeEnum;
import wsg.lol.common.pojo.dto.summoner.ChampionMasteryDto;
import wsg.lol.common.pojo.dto.summoner.LeagueEntryDto;
import wsg.lol.common.pojo.dto.summoner.SummonerDto;
import wsg.lol.common.pojo.dto.system.EventDto;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.dao.api.impl.ChampionMasteryV4;
import wsg.lol.dao.api.impl.LeagueV4;
import wsg.lol.dao.api.impl.SummonerV4;
import wsg.lol.dao.mybatis.mapper.summoner.ChampionMasteryMapper;
import wsg.lol.dao.mybatis.mapper.summoner.LeagueEntryMapper;
import wsg.lol.dao.mybatis.mapper.summoner.SummonerMapper;
import wsg.lol.dao.mybatis.mapper.system.EventMapper;
import wsg.lol.service.common.MapperExecutor;
import wsg.lol.service.system.intf.EventHandler;

import java.util.Date;
import java.util.List;

/**
 * Handler for events of {@link EventTypeEnum#SummonerId}
 *
 * @author Kingen
 */
@Service(value = "SummonerIdEventHandler")
public class SummonerIdEventHandler implements EventHandler {

    private static final Logger logger = LoggerFactory.getLogger(SummonerIdEventHandler.class);

    private SummonerV4 summonerV4;

    private SummonerMapper summonerMapper;

    private TransactionTemplate transactionTemplate;

    private ChampionMasteryV4 championMasteryV4;

    private ChampionMasteryMapper championMasteryMapper;

    private LeagueV4 leagueV4;

    private LeagueEntryMapper leagueEntryMapper;

    private EventMapper eventMapper;

    @Override
    public synchronized Result handle(List<EventDto> events) {
        for (EventDto event : events) {
            String summonerId = event.getContext();
            try {
                transactionTemplate.execute(transactionStatus -> {
                    logger.info("Handling the event of {}.", summonerId);

                    // if exists.
                    SummonerDto summonerDto = summonerMapper.selectByPrimaryKey(summonerId);
                    if (summonerDto != null) {
                        return ResultUtils.success();
                    }

                    // get base info.
                    SummonerDto summoner = summonerV4.getSummonerById(summonerId);
                    int score = championMasteryV4.getScoreBySummonerId(summonerId);
                    summoner.setScore(score);

                    logger.info("Adding champion masteries of {}.", summonerId);
                    List<ChampionMasteryDto> championMasteries = championMasteryV4.getChampionMasteryBySummonerId(summonerId);
                    Result result = MapperExecutor.insertList(championMasteryMapper, championMasteries);
                    ResultUtils.assertSuccess(result);

                    logger.info("Adding league entries of {}.", summonerId);
                    List<LeagueEntryDto> entries = leagueV4.getLeagueEntriesBySummonerId(summonerId);
                    result = MapperExecutor.insertList(leagueEntryMapper, entries);
                    ResultUtils.assertSuccess(result);

                    logger.info("Adding the summoner {}.", summonerId);
                    summoner.setLastUpdate(new Date());
                    int count = summonerMapper.insert(summoner);
                    if (count != 1) {
                        logger.error("Failed to inert the summoner {}.", summonerId);
                        throw new AppException(ErrorCodeConst.DATABASE_ERROR, "Failed to inert the summoner " + summonerId);
                    }

                    logger.info("Updating the status of the event {} to {}.", summonerId, EventStatusEnum.Finished);
                    count = eventMapper.updateStatus(EventTypeEnum.SummonerId, summonerId, EventStatusEnum.Unfinished, EventStatusEnum.Finished);
                    if (count != 1) {
                        logger.error("Failed to update the status of the event {} to {}.", summonerId, EventStatusEnum.Finished);
                        throw new AppException(ErrorCodeConst.DATABASE_ERROR, "Failed to update the status of the event. ");
                    }

                    logger.info("Succeed in handling the event of {}.", summonerId);
                    return ResultUtils.success();
                });
            } catch (AppException e) {
                e.printStackTrace();
            }
        }

        logger.info("Succeed in handling the events of summoner id.");
        return ResultUtils.success();
    }

    @Autowired
    public void setEventMapper(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
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
    public void setChampionMasteryV4(ChampionMasteryV4 championMasteryV4) {
        this.championMasteryV4 = championMasteryV4;
    }

    @Autowired
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    @Autowired
    public void setSummonerMapper(SummonerMapper summonerMapper) {
        this.summonerMapper = summonerMapper;
    }

    @Autowired
    public void setSummonerV4(SummonerV4 summonerV4) {
        this.summonerV4 = summonerV4;
    }
}
