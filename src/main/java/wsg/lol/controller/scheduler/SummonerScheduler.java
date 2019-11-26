package wsg.lol.controller.scheduler;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import wsg.lol.common.base.AppException;
import wsg.lol.common.pojo.dto.summoner.SummonerDto;
import wsg.lol.common.util.PageUtils;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.service.intf.ChampionService;
import wsg.lol.service.intf.LeagueService;
import wsg.lol.service.intf.SummonerService;
import wsg.lol.service.system.intf.SystemService;

import java.util.List;

/**
 * Scheduler for data of summoners.
 *
 * @author Kingen
 */
@Service
public class SummonerScheduler {

    private static final Logger logger = LoggerFactory.getLogger(SummonerScheduler.class);

    private SummonerService summonerService;

    private SystemService systemService;

    private TransactionTemplate transactionTemplate;

    private ChampionService championService;

    private LeagueService leagueService;

    //    @Scheduled(fixedDelay = DateUtils.MILLIS_PER_MINUTE)
    public void updateSummoners() {
        logger.info("Schedule to update summoners.");
        List<SummonerDto> summoners = summonerService.getSummonersForUpdate(PageUtils.getRowBounds()).getList();
        for (SummonerDto summoner : summoners) {
            String summonerId = summoner.getId();
            try {
                transactionTemplate.execute(transactionStatus -> {
                    logger.info("Update summoner {}.", summonerId);
                    ResultUtils.assertSuccess(championService.updateChampionMasteries(summonerId));
                    ResultUtils.assertSuccess(leagueService.updateLeagueEntry(summonerId));
                    ResultUtils.assertSuccess(summonerService.updateSummoner(summonerId));
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

    @Scheduled(fixedDelay = DateUtils.MILLIS_PER_MINUTE)
    public void addMatches() {
    }

    @Autowired
    public void setChampionService(ChampionService championService) {
        this.championService = championService;
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
