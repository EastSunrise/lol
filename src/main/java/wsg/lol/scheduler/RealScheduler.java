package wsg.lol.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import wsg.lol.common.base.AppException;
import wsg.lol.common.pojo.dto.summoner.SummonerDto;
import wsg.lol.common.util.PageUtils;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.service.intf.LeagueService;
import wsg.lol.service.intf.MatchService;
import wsg.lol.service.intf.SummonerService;

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

    private TransactionTemplate transactionTemplate;

    private LeagueService leagueService;

    private MatchService matchService;

    /**
     * todo 进程调度算法选择summoners
     * Update the summoners with the most early update time.
     */
    @Scheduled(initialDelay = AsyncConfig.INITIAL_DELAY, fixedDelay = AsyncConfig.FIXED_DELAY)
    public void updateSummoners() {
        logger.info("Getting summoners for update...");
        List<SummonerDto> summoners = summonerService.getSummonersForUpdate(PageUtils.getRowBounds()).getList();
        logger.info("Got {} summoners for update. Handling...", summoners.size());
        final int[] success = {0};
        for (SummonerDto summoner : summoners) {
            String summonerId = summoner.getId();
            try {
                transactionTemplate.execute(transactionStatus -> {
                    ResultUtils.assertSuccess(summonerService.updateChampionMasteries(summonerId));
                    ResultUtils.assertSuccess(leagueService.updateLeagueEntry(summonerId));
                    ResultUtils.assertSuccess(summonerService.updateSummonerInfo(summonerId));
                    logger.info("Succeed in updating the summoner {}.", summonerId);
                    success[0]++;
                    return ResultUtils.success();
                });
            } catch (AppException e) {
                logger.error("Failed to update summoner {}", summonerId);
                e.printStackTrace();
            }
        }

        logger.info("Summoners updated, {} succeeded, {} failed.", success[0], summoners.size() - success[0]);
    }

    /**
     * Add events of the matches after the last time updating the matches.
     */
    @Scheduled(initialDelay = AsyncConfig.INITIAL_DELAY, fixedDelay = AsyncConfig.FIXED_DELAY)
    public void updateMatches() throws AppException {
        logger.info("Getting summoners for match...");
        List<SummonerDto> summoners = summonerService.getSummonersForMatch(PageUtils.getRowBounds()).getList();
        logger.info("Got {} summoners for match. Handling...", summoners.size());
        int success = 0;
        for (SummonerDto summoner : summoners) {
            try {
                ResultUtils.assertSuccess(matchService.updateMatches(summoner.getAccountId(), summoner.getLastMatch()));
                success++;
            } catch (AppException e) {
                logger.error("Failed to update matches of the account {}", summoner.getAccountId());
                e.printStackTrace();
            }
        }
        logger.info("Matches updated, {} succeeded, {} failed.", success, summoners.size() - success);
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
    public void setSummonerService(SummonerService summonerService) {
        this.summonerService = summonerService;
    }
}
