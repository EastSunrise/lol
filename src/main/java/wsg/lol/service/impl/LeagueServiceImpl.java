package wsg.lol.service.impl;

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
import wsg.lol.common.pojo.transfer.ObjectTransfer;
import wsg.lol.common.task.AbstractBatchTask;
import wsg.lol.common.task.MinTaskStrategy;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.dao.api.impl.LeagueV4;
import wsg.lol.dao.mybatis.mapper.region.summoner.LeagueEntryMapper;
import wsg.lol.service.common.MapperExecutor;
import wsg.lol.service.intf.EventService;
import wsg.lol.service.intf.LeagueService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

        List<String> ids = new ArrayList<>();
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
                            ids.add(leagueEntryDto.getSummonerId());
                        }
                        if (ids.size() > AbstractBatchTask.MAX_SIZE) {
                            count += batchInsertEvents(forkJoinPool, ids);
                            ids = new ArrayList<>();
                        }
                    }
                }
            }
        }

        count += batchInsertEvents(forkJoinPool, ids);
        logger.info("{} events of summoners inserted totally.", count);
        return ResultUtils.success();
    }

    private int batchInsertEvents(ForkJoinPool forkJoinPool, List<String> ids) {
        AbstractBatchTask<String, GenericResult<Integer>> task = new AbstractBatchTask<>(ids, new MinTaskStrategy<String, GenericResult<Integer>>() {
            @Override
            public GenericResult<Integer> doMinTask(List<String> strings) {
                return eventService.insertEvents(EventTypeEnum.Summoner, new HashSet<>(strings));
            }

            @Override
            public GenericResult<Integer> joinResult(GenericResult<Integer> r1, GenericResult<Integer> r2) {
                GenericResult<Integer> result = new GenericResult<>();
                result.setObject(r1.getObject() + r2.getObject());
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
