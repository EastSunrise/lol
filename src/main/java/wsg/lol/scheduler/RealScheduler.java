package wsg.lol.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import wsg.lol.common.base.AppException;
import wsg.lol.common.base.Result;
import wsg.lol.common.pojo.dto.summoner.SummonerDto;
import wsg.lol.common.util.PageUtils;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.service.intf.LeagueService;
import wsg.lol.service.intf.MatchService;
import wsg.lol.service.intf.SummonerService;
import wsg.lol.service.intf.SystemService;

import java.util.List;

/**
 * Scheduler for data of summoners.
 *
 * @author Kingen
 */
@Component
@Async
public class RealScheduler {

    private static final Logger logger = LoggerFactory.getLogger(RealScheduler.class);

    private SummonerService summonerService;

    private SystemService systemService;

    private TransactionTemplate transactionTemplate;

    private LeagueService leagueService;

    private MatchService matchService;

    /**
     * todo 进程调度算法选择summoners
     * Update the summoners with the most early update time.
     */
//    @Scheduled(fixedDelay = DateUtils.MILLIS_PER_MINUTE)
    public void updateSummoners() {
        logger.info("Schedule to update summoners.");
        List<SummonerDto> summoners = summonerService.getSummonersForUpdate(PageUtils.getRowBounds()).getList();
        for (SummonerDto summoner : summoners) {
            String summonerId = summoner.getId();
            try {
                transactionTemplate.execute(transactionStatus -> {
                    logger.info("Update summoner {}.", summonerId);
                    ResultUtils.assertSuccess(summonerService.updateChampionMasteries(summonerId));
                    ResultUtils.assertSuccess(leagueService.updateLeagueEntry(summonerId));
                    ResultUtils.assertSuccess(summonerService.updateSummonerInfo(summonerId));
                    logger.info("Succeed in updating the summoner {}.", summonerId);
                    return ResultUtils.success();
                });
            } catch (AppException e) {
                logger.error("Failed to update summoner {}", summonerId);
                systemService.sendWarnMessage(ResultUtils.fail(e));
                e.printStackTrace();
            }
        }
    }

    /**
     * Add events of the matches after the last time updating the matches.
     */
//    @Scheduled(fixedDelay = DateUtils.MILLIS_PER_MINUTE)
    public void updateMatches() throws AppException {
        logger.info("Schedule to update matches.");
        List<SummonerDto> summoners = summonerService.getSummonersForMatch(PageUtils.getRowBounds()).getList();
        for (SummonerDto summoner : summoners) {
            Result result;
            try {
                result = matchService.updateMatches(summoner.getAccountId(), summoner.getLastMatch());
            } catch (AppException e) {
                result = ResultUtils.fail(e);
                e.printStackTrace();
            }
            systemService.sendWarnMessage(result);
        }
    }

    @Autowired
    public void setMatchService(MatchService matchService) {
        this.matchService = matchService;
    }

    @Autowired
    public void setLeagueService(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @Autowired
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    @Autowired
    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }

    @Autowired
    public void setSummonerService(SummonerService summonerService) {
        this.summonerService = summonerService;
    }
}