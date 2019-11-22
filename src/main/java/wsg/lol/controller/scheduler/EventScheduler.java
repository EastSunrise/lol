package wsg.lol.controller.scheduler;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import wsg.lol.common.base.Result;
import wsg.lol.common.enums.system.EventTypeEnum;
import wsg.lol.service.system.intf.EventHandler;
import wsg.lol.service.system.intf.SystemService;

/**
 * Scheduler for events.
 *
 * @author Kingen
 */
@Service
public class EventScheduler {

    private static final Logger logger = LoggerFactory.getLogger(EventHandler.class);

    private SystemService systemService;

    @Scheduled(fixedRate = DateUtils.MILLIS_PER_HOUR)
    public void addSummoners() {
        logger.info("Adding summoners by event.");
        Result handle = systemService.handle(EventTypeEnum.SummonerId);
        if (!handle.isSuccess()) {
            systemService.sendWarnMessage(handle);
            return;
        }

        logger.info("Succeed in adding summoners by event.");
    }

    @Autowired
    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }
}