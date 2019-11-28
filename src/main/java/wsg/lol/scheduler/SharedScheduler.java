package wsg.lol.scheduler;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import wsg.lol.common.base.Result;
import wsg.lol.common.result.system.VersionResult;
import wsg.lol.service.intf.SharedService;
import wsg.lol.service.intf.SystemService;

/**
 * Scheduler for shared data.
 *
 * @author Kingen
 */
@Component
@Async
public class SharedScheduler {

    private static final Logger logger = LoggerFactory.getLogger(SharedScheduler.class);

    private SystemService systemService;

    private SharedService sharedService;

    @Scheduled(fixedRate = DateUtils.MILLIS_PER_HOUR)
    public void checkVersion() {
        logger.info("Schedule to check the version...");
        VersionResult versionResult = systemService.getVersion();
        if (versionResult.isLatestVersion()) {
            logger.info("The version is latest.");
            return;
        }

        String message = "The latest version is " + versionResult.getLatestVersion() + ". Please update the version.";
        logger.info(message);
        systemService.sendMessage(message);
    }

    @Scheduled(fixedRate = DateUtils.MILLIS_PER_HOUR)
    public void updateGSharedData() {
        logger.info("Schedule to update the shared data.");
        Result result = sharedService.updateSharedStatus();
        systemService.sendWarnMessage(result);

        result = sharedService.updateChampionRotation();
        systemService.sendWarnMessage(result);
    }

    @Autowired
    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }

    @Autowired
    public void setSharedService(SharedService sharedService) {
        this.sharedService = sharedService;
    }
}
