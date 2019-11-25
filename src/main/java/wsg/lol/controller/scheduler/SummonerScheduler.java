package wsg.lol.controller.scheduler;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import wsg.lol.service.intf.SummonerService;

/**
 * Scheduler for data of summoners.
 *
 * @author Kingen
 */
@Service
public class SummonerScheduler {

    private static final Logger logger = LoggerFactory.getLogger(SummonerScheduler.class);

    private SummonerService summonerService;

    @Scheduled(fixedDelay = DateUtils.MILLIS_PER_HOUR)
    public void updateSummoners() {

    }

    @Autowired
    public void setSummonerService(SummonerService summonerService) {
        this.summonerService = summonerService;
    }
}
