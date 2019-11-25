package wsg.lol.controller.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wsg.lol.common.base.Result;
import wsg.lol.common.pojo.dto.match.MatchDto;
import wsg.lol.common.pojo.dto.match.MatchListDto;
import wsg.lol.common.pojo.query.QueryMatchListDto;
import wsg.lol.common.util.PageUtils;
import wsg.lol.dao.api.impl.MatchV4;
import wsg.lol.service.intf.SummonerService;
import wsg.lol.service.system.intf.SystemService;

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

    //    @Scheduled(fixedDelay = DateUtils.MILLIS_PER_MINUTE)
    public void updateSummoners() {
        logger.info("Schedule to update summoners.");
        Result result = summonerService.updateSummoners(PageUtils.getRowBounds());
        systemService.sendWarnMessage(result);
    }

    //    @Scheduled(fixedDelay = DateUtils.MILLIS_PER_MINUTE)
    public void addMatches() {
        MatchListDto matchListDto = matchV4.getMatchListByAccount("H_XUKllSjweFXSMtC4Ct4AHpfT_XCPkUTInzLqJ0Vebtmz8", new QueryMatchListDto());
        Long gameId = matchListDto.getMatches().get(0).getGameId();
        MatchDto match = matchV4.getMatchById(gameId);
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
