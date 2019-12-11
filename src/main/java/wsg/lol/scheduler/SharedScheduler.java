package wsg.lol.scheduler;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import wsg.lol.common.result.system.VersionResult;
import wsg.lol.service.context.SharedContext;
import wsg.lol.service.intf.SystemService;

/**
 * Scheduler for shared data.
 *
 * @author Kingen
 */
@Component
public class SharedScheduler {

    private static final Logger logger = LoggerFactory.getLogger(SharedScheduler.class);

    private SystemService systemService;

    private SharedContext sharedContext;

    /**
     * Check the version.
     */
    @Scheduled(cron = "${cron.share.version}")
    public void checkVersion() {
        logger.info("Scheduling to check the version...");
        VersionResult versionResult = systemService.getVersion();
        if (versionResult.isLatestVersion()) {
            logger.info("The version is latest.");
            return;
        }

        String message = "The latest version is " + versionResult.getLatestVersion() + ". Please update the version.";
        logger.info(message);
        systemService.sendMessage(message);
    }

    /**
     * Update the shard status and the rotation of champions.
     * Request 2.
     */
    @Scheduled(cron = "${cron.share.shard}")
    public void updateSharedData() {
        logger.info("Scheduling to update the shared data.");
        sharedContext.updateShardStatus();
        sharedContext.updateChampionRotation();
    }

    /**
     * Update the featured games.
     * Request 1.
     */
    @Scheduled(fixedDelay = DateUtils.MILLIS_PER_MINUTE)
    public void updateFeaturedGames() {
        logger.info("Scheduling to update the featured games.");
        sharedContext.updateFeaturedGames();
    }

    @Autowired
    public void setSharedContext(SharedContext sharedContext) {
        this.sharedContext = sharedContext;
    }

    @Autowired
    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }
}
