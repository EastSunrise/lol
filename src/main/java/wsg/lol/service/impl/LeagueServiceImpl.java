package wsg.lol.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wsg.lol.common.annotation.Performance;
import wsg.lol.common.base.GenericResult;
import wsg.lol.common.base.Result;
import wsg.lol.common.enums.share.RankQueueEnum;
import wsg.lol.common.enums.summoner.DivisionEnum;
import wsg.lol.common.enums.summoner.TierEnum;
import wsg.lol.common.enums.system.EventTypeEnum;
import wsg.lol.common.pojo.dto.summoner.LeagueEntryDto;
import wsg.lol.common.task.AbstractBatchTask;
import wsg.lol.common.task.MapTaskStrategy;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.dao.api.impl.LeagueV4;
import wsg.lol.service.intf.EventService;
import wsg.lol.service.intf.LeagueService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

/**
 * @author Kingen
 */
@Service
public class LeagueServiceImpl implements LeagueService {

    private static final Logger logger = LoggerFactory.getLogger(LeagueService.class);

    private LeagueV4 leagueV4;

    private EventService eventService;

    @Override
    @Performance
    public Result initializeByLeagues() {
        logger.info("Initializing the database by leagues.");

        Map<String, String> map = new HashMap<>();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int count = 0;

        for (RankQueueEnum queue : RankQueueEnum.values()) {
            for (TierEnum tier : TierEnum.RANKED_TIERS) {
                for (DivisionEnum division : DivisionEnum.validDivisions(tier)) {
                    for (int page = 1; ; page++) {
                        List<LeagueEntryDto> leagueEntryDtoList = leagueV4.getLeagueEntriesExp(queue, tier, division, page);
                        if (leagueEntryDtoList == null || leagueEntryDtoList.isEmpty())
                            break;
                        for (LeagueEntryDto leagueEntryDto : leagueEntryDtoList) {
                            map.put(StringUtils.deleteWhitespace(leagueEntryDto.getSummonerName()), StringUtils.joinWith(".", queue, tier, division, page));
                        }
                        if (map.size() > AbstractBatchTask.MAX_SIZE) {
                            count += batchInsertEvents(forkJoinPool, map);
                            map = new HashMap<>();
                        }
                    }
                }
            }
        }

        count += batchInsertEvents(forkJoinPool, map);
        logger.info("{} events of summoners inserted totally.", count);
        return ResultUtils.success();
    }

    private int batchInsertEvents(ForkJoinPool forkJoinPool, Map<String, String> map) {
        AbstractBatchTask<Map<String, String>, GenericResult<Integer>> task = new AbstractBatchTask<>(map, new MapTaskStrategy<String, String, GenericResult<Integer>>() {
            @Override
            public GenericResult<Integer> doMinTask(Map<String, String> map) {
                return eventService.insertEvents(EventTypeEnum.Summoner, map);
            }

            @Override
            public GenericResult<Integer> joinResult(List<GenericResult<Integer>> results) {
                int sum = 0;
                for (GenericResult<Integer> result : results) {
                    sum += result.getObject();
                }
                return GenericResult.create(sum);
            }
        });

        forkJoinPool.execute(task);
        return task.join().getObject();
    }

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setLeagueV4(LeagueV4 leagueV4) {
        this.leagueV4 = leagueV4;
    }
}
