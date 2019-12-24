package wsg.lol.service.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wsg.lol.common.constant.ConfigConst;
import wsg.lol.common.enums.system.EventStatusEnum;
import wsg.lol.common.enums.system.EventTypeEnum;
import wsg.lol.common.pojo.domain.summoner.ChampionMasteryDo;
import wsg.lol.common.pojo.domain.summoner.LeagueEntryDo;
import wsg.lol.common.pojo.domain.summoner.SummonerDo;
import wsg.lol.common.pojo.domain.system.EventDo;
import wsg.lol.common.pojo.dto.summoner.ChampionMasteryDto;
import wsg.lol.common.pojo.dto.summoner.LeagueEntryDto;
import wsg.lol.common.pojo.dto.summoner.SummonerDto;
import wsg.lol.common.pojo.transfer.ObjectTransfer;
import wsg.lol.dao.api.impl.ChampionMasteryV4;
import wsg.lol.dao.api.impl.LeagueV4;
import wsg.lol.dao.api.impl.SummonerV4;
import wsg.lol.dao.mybatis.mapper.region.summoner.ChampionMasteryMapper;
import wsg.lol.dao.mybatis.mapper.region.summoner.LeagueEntryMapper;
import wsg.lol.dao.mybatis.mapper.region.summoner.SummonerMapper;
import wsg.lol.service.base.BaseService;
import wsg.lol.service.intf.EventService;
import wsg.lol.service.intf.SummonerService;

import java.util.Date;
import java.util.List;

/**
 * Handler for events of type {@link EventTypeEnum#Summoner}
 *
 * @author Kingen
 */
@Service(value = "SummonerEventHandler")
public class SummonerEventHandler extends BaseService implements EventHandler {

    private static final Logger logger = LoggerFactory.getLogger(SummonerEventHandler.class);

    private SummonerV4 summonerV4;

    private ChampionMasteryV4 championMasteryV4;

    private LeagueV4 leagueV4;

    private SummonerMapper summonerMapper;

    private ChampionMasteryMapper championMasteryMapper;

    private LeagueEntryMapper leagueEntryMapper;

    private SummonerService summonerService;

    private EventService eventService;

    @Override
    @Transactional
    public void handle(EventDo event) {
        String summonerId = event.getContext();
        logger.info("Adding the summoner {}...", summonerId);

        SummonerDto summonerById = summonerService.getSummonersById(summonerId);
        if (summonerById != null) {
            logger.info("Summoner {} exists already.", summonerId);
            return;
        }

        SummonerDto summonerDto = summonerV4.getSummonerById(summonerId);

        SummonerDo summonerDo = ObjectTransfer.transferDto(summonerDto, SummonerDo.class);
        int score = championMasteryV4.getScoreBySummonerId(summonerId);
        summonerDo.setScore(score);
        summonerDo.setLastUpdate(new Date());
        summonerDo.setLastMatch(ConfigConst.MATCH_BEGIN_DATE);
        insert(summonerMapper, summonerDo);

        logger.info("Adding champion masteries of {}...", summonerId);
        List<ChampionMasteryDto> championMasteries = championMasteryV4.getChampionMasteryBySummonerId(summonerId);
        insertList(championMasteryMapper, ObjectTransfer.transferDtoList(championMasteries, ChampionMasteryDo.class));

        logger.info("Adding league entries of {}...", summonerId);
        List<LeagueEntryDto> entries = leagueV4.getLeagueEntriesBySummonerId(summonerId);
        insertList(leagueEntryMapper, ObjectTransfer.transferDtoList(entries, LeagueEntryDo.class));

        eventService.updateStatus(EventTypeEnum.Summoner, summonerId, EventStatusEnum.Unfinished, EventStatusEnum.Finished);

        logger.info("Added the summoner {}.", summonerId);
    }

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setSummonerService(SummonerService summonerService) {
        this.summonerService = summonerService;
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
