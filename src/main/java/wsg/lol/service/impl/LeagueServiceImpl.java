package wsg.lol.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wsg.lol.common.LeagueEntryDo;
import wsg.lol.common.annotation.Performance;
import wsg.lol.common.base.Result;
import wsg.lol.common.enums.game.DivisionEnum;
import wsg.lol.common.enums.game.RankQueueEnum;
import wsg.lol.common.enums.game.TierEnum;
import wsg.lol.common.enums.system.EventTypeEnum;
import wsg.lol.common.pojo.dto.summoner.LeagueEntryDto;
import wsg.lol.common.pojo.transfer.ObjectTransfer;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.dao.api.impl.LeagueV4;
import wsg.lol.dao.mybatis.mapper.summoner.LeagueEntryMapper;
import wsg.lol.service.common.MapperExecutor;
import wsg.lol.service.intf.EventService;
import wsg.lol.service.intf.LeagueService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kingen
 */
@Service
public class LeagueServiceImpl implements LeagueService {

    private static final Logger logger = LoggerFactory.getLogger(LeagueService.class);

    private static final int MAX_SIZE = 1000;

    private LeagueV4 leagueV4;

    private LeagueEntryMapper leagueEntryMapper;

    private EventService eventService;

    @Override
    @Performance
    public Result initialByLeagues() {
        logger.info("Initializing the database by leagues.");
        List<String> ids = new ArrayList<>();
        for (RankQueueEnum queue : RankQueueEnum.values()) {
            for (TierEnum tier : TierEnum.RANKED_TIERS) {
                for (DivisionEnum division : DivisionEnum.validDivisions(tier)) {
                    for (int page = 1; ; page++) {
                        List<LeagueEntryDto> leagueEntryDtoList = leagueV4.getLeagueEntriesExp(queue, tier, division, page);
                        if (leagueEntryDtoList == null || leagueEntryDtoList.isEmpty())
                            break;
                        for (LeagueEntryDto leagueEntryDto : leagueEntryDtoList) {
                            ids.add(leagueEntryDto.getSummonerId());
                        }
                        if (ids.size() > MAX_SIZE) {
                            eventService.insertEvents(EventTypeEnum.Summoner, ids);
                            ids = new ArrayList<>();
                        }
                    }
                }
            }
        }

        eventService.insertEvents(EventTypeEnum.Summoner, ids);
        return ResultUtils.success();
    }

    @Override
    @Transactional
    @Performance
    public Result updateLeagueEntry(String summonerId) {
        logger.info("Updating league entries of {}.", summonerId);
        List<LeagueEntryDto> entries = leagueV4.getLeagueEntriesBySummonerId(summonerId);
        ResultUtils.assertSuccess(MapperExecutor.updateList(leagueEntryMapper, ObjectTransfer.transferList(entries, LeagueEntryDo.class)));
        return ResultUtils.success();
    }

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setLeagueEntryMapper(LeagueEntryMapper leagueEntryMapper) {
        this.leagueEntryMapper = leagueEntryMapper;
    }

    @Autowired
    public void setLeagueV4(LeagueV4 leagueV4) {
        this.leagueV4 = leagueV4;
    }
}
