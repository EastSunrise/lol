package wsg.lol.controller.scheduler;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import wsg.lol.common.base.Result;
import wsg.lol.common.result.version.VersionResult;
import wsg.lol.service.intf.MessageService;
import wsg.lol.service.intf.SharedService;
import wsg.lol.service.intf.VersionService;

/**
 * // TODO: (Kingen, 2019/11/21) *
 *
 * @author Kingen
 */
@Service
public class Scheduler {

    private static Logger logger = LoggerFactory.getLogger(Scheduler.class);

    private VersionService versionService;

    private MessageService messageService;

    private SharedService sharedService;

    @Scheduled(fixedRate = DateUtils.MILLIS_PER_DAY)
    public void checkVersion() {
        logger.info("Checking the version...");
        VersionResult versionResult = versionService.getVersion();
        if (versionResult.isLatestVersion()) {
            logger.info("The version is latest.");
            return;
        }

        String message = "The latest version is " + versionResult.getLatestVersion() + ". Please update the version.";
        logger.info(message);
        messageService.sendMessage(message);
    }

    @Scheduled(fixedRate = DateUtils.MILLIS_PER_HOUR)
    public void updateGeneralData() {
        Result result = sharedService.updateSharedStatus();
        messageService.sendWarnMessage(result);

        result = sharedService.updateChampionRotation();
        messageService.sendWarnMessage(result);
    }

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @Autowired
    public void setVersionService(VersionService versionService) {
        this.versionService = versionService;
    }

    @Autowired
    public void setSharedService(SharedService sharedService) {
        this.sharedService = sharedService;
    }
}
