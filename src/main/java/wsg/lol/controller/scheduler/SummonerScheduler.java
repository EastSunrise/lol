package wsg.lol.controller.scheduler;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import wsg.lol.common.base.AppException;
import wsg.lol.common.pojo.dto.match.MatchDto;
import wsg.lol.common.pojo.dto.match.MatchListDto;
import wsg.lol.common.pojo.dto.match.MatchTimelineDto;
import wsg.lol.common.pojo.dto.summoner.SummonerDto;
import wsg.lol.common.pojo.query.QueryMatchListDto;
import wsg.lol.common.util.PageUtils;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.dao.api.impl.MatchV4;
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

    private MatchV4 matchV4;

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
                    championService.updateChampionMasteries(summonerId);
                    leagueService.updateLeagueEntry(summonerId);
                    summonerService.updateSummoner(summonerId);
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
        MatchListDto matchListDto = matchV4.getMatchListByAccount("QAewsqwOvAq6hPd82G2EFXDgchdy6ODcdpDvMtvYh422", new QueryMatchListDto());
        Long gameId = matchListDto.getMatches().get(0).getGameId();
        MatchDto match = matchV4.getMatchById(gameId);
        MatchTimelineDto timeline = matchV4.getTimelineByMatchId(gameId);
        System.out.println();
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
    public void setMatchV4(MatchV4 matchV4) {
        this.matchV4 = matchV4;
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
