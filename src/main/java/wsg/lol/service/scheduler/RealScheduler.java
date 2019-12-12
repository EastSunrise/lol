package wsg.lol.service.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import wsg.lol.common.base.AppException;
import wsg.lol.common.pojo.dto.summoner.SummonerDto;
import wsg.lol.common.pojo.query.QueryMatchListDto;
import wsg.lol.common.util.PageUtils;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.service.intf.MatchService;
import wsg.lol.service.intf.SummonerService;

import javax.xml.ws.http.HTTPException;
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

    private MatchService matchService;

    /**
     * Update the summoners with the most early update time.
     * Request 4.
     */
    @Scheduled(cron = "${cron.update.summoner}")
    public void updateSummoners() {
        logger.info("Getting summoners for update...");
        List<SummonerDto> summoners = summonerService.getSummonersForUpdate(PageUtils.getRowBounds()).getList();
        logger.info("Got {} summoners for update. Handling...", summoners.size());

        int success = 0;
        for (SummonerDto summoner : summoners) {
            String summonerId = summoner.getId();
            try {
                ResultUtils.assertSuccess(summonerService.updateSummoner(summonerId));
                success++;
            } catch (AppException e) {
                logger.error("Failed to update summoner {}", summonerId);
                e.printStackTrace();
            }
        }

        logger.info("Summoners updated, {} succeeded, {} failed.", success, summoners.size() - success);
    }

    /**
     * Add events of the matches after the last time updating the matches.
     * Request 1.
     */
    @Scheduled(cron = "${cron.update.match}")
    public void updateMatches() throws AppException {
        logger.info("Getting summoners for match...");
        List<SummonerDto> summoners = summonerService.getSummonersForMatch(PageUtils.getRowBounds()).getList();
        logger.info("Got {} summoners for match. Handling...", summoners.size());

        int success = 0;
        for (SummonerDto summoner : summoners) {
            try {
                ResultUtils.assertSuccess(matchService.updateMatches(summoner.getAccountId(), summoner.getLastMatch()));
                success++;
            } catch (HTTPException | AppException e) {
                logger.error("{}: Failed to update matches of the account {}", e.getMessage(), summoner.getAccountId());
                summonerService.updateSummonerLastMatch(summoner.getAccountId(), QueryMatchListDto.getLastDate());
            } catch (RuntimeException e) {
                logger.error("Failed to update matches of the account {}", summoner.getAccountId());
                e.printStackTrace();
                summonerService.updateSummonerLastMatch(summoner.getAccountId(), QueryMatchListDto.getLastDate());
            }
        }
        logger.info("Matches updated, {} succeeded, {} failed.", success, summoners.size() - success);
    }

    @Autowired
    public void setMatchService(MatchService matchService) {
        this.matchService = matchService;
    }

    @Autowired
    public void setSummonerService(SummonerService summonerService) {
        this.summonerService = summonerService;
    }
}
