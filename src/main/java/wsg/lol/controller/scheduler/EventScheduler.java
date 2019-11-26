package wsg.lol.controller.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wsg.lol.common.base.Result;
import wsg.lol.common.enums.system.EventTypeEnum;
import wsg.lol.common.util.PageUtils;
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

    //    @Scheduled(fixedDelay = DateUtils.MILLIS_PER_MINUTE)
    public void addSummoners() {
        logger.info("Adding summoners by event.");
        Result handle = systemService.handle(EventTypeEnum.SummonerId, PageUtils.getRowBounds());
        systemService.sendWarnMessage(handle);
    }

    @Autowired
    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }
}
