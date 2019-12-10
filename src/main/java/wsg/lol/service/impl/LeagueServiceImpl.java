package wsg.lol.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wsg.lol.common.annotation.Performance;
import wsg.lol.common.base.GenericResult;
import wsg.lol.common.base.Result;
import wsg.lol.common.enums.share.RankQueueEnum;
import wsg.lol.common.enums.summoner.DivisionEnum;
import wsg.lol.common.enums.summoner.TierEnum;
import wsg.lol.common.enums.system.EventTypeEnum;
import wsg.lol.common.pojo.domain.summoner.LeagueEntryDo;
import wsg.lol.common.pojo.dto.summoner.LeagueEntryDto;
import wsg.lol.common.task.AbstractBatchTask;
import wsg.lol.common.task.MapTaskStrategy;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.dao.api.impl.LeagueV4;
import wsg.lol.dao.common.transfer.ObjectTransfer;
import wsg.lol.dao.mybatis.mapper.region.summoner.LeagueEntryMapper;
import wsg.lol.service.common.MapperExecutor;
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

    private LeagueEntryMapper leagueEntryMapper;

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
                            map.put(leagueEntryDto.getSummonerId(), StringUtils.joinWith(".", queue, tier, division, page));
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
                GenericResult<Integer> result = new GenericResult<>();
                result.setObject(sum);
                return result;
            }
        });

        forkJoinPool.execute(task);
        return task.join().getObject();
    }

    @Override
    @Transactional
    @Performance
    public Result updateLeagueEntry(String summonerId) {
        logger.info("Updating league entries of {}...", summonerId);
        List<LeagueEntryDto> entries = leagueV4.getLeagueEntriesBySummonerId(summonerId);
        ResultUtils.assertSuccess(MapperExecutor.updateList(leagueEntryMapper, ObjectTransfer.transferDtoList(entries, LeagueEntryDo.class)));
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
