package wsg.lol.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import wsg.lol.common.base.AppException;
import wsg.lol.common.pojo.dto.summoner.SummonerDto;
import wsg.lol.common.util.PageUtils;
import wsg.lol.service.intf.SummonerService;

import java.util.List;

/**
 * Scheduler for data of summoners.
 *
 * @author Kingen
 */
@Component
public class RealScheduler {

    private static final Logger logger = LoggerFactory.getLogger(RealScheduler.class);

    private SummonerService summonerService;

    /**
     * Update the summoners with the most early update time.
     * Request 4.
     */
    @Scheduled(cron = "${cron.update.summoner}")
    public void updateSummoners() {
        logger.info("Getting summoners for update...");
        List<? extends SummonerDto> summoners = summonerService.getSummonersForUpdate(PageUtils.DEFAULT_PAGE);
        logger.info("Got {} summoners for update. Handling...", summoners.size());

        int success = 0;
        for (SummonerDto summoner : summoners) {
            String summonerId = summoner.getId();
            try {
                summonerService.updateSummoner(summonerId);
                success++;
            } catch (Exception e) {
                logger.error("Failed to update the summoner {}.", summonerId);
                logger.error(e.getMessage(), e);
            }
        }

        logger.info("Summoners updated, {} succeeded, {} failed.", success, summoners.size() - success);
    }

    /**
     * Add events of the matches after the last time updating the matches.
     * Request 1.
     */
    @Scheduled(fixedDelay = 1)
    public void updateMatches() throws AppException {
        logger.info("Getting summoners for match...");
        List<? extends SummonerDto> summoners = summonerService.getSummonersForMatch(PageUtils.DEFAULT_PAGE);
        logger.info("Got {} summoners for match. Handling...", summoners.size());

        int success = 0;
        for (SummonerDto summoner : summoners) {
            String accountId = summoner.getAccountId();
            try {
                summonerService.updateMatches(accountId, summoner.getLastMatch());
                success++;
            } catch (Exception e) {
                logger.error("Failed to update matches of the account {}.", accountId);
                logger.error(e.getMessage(), e);
            }
        }

        logger.info("Matches updated, {} succeeded, {} failed.", success, summoners.size() - success);
    }

    @Autowired
    public void setSummonerService(SummonerService summonerService) {
        this.summonerService = summonerService;
    }
}
