package wsg.lol.controller.scheduler;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import wsg.lol.common.base.Result;
import wsg.lol.common.enums.system.EventTypeEnum;
import wsg.lol.common.util.PageUtils;
import wsg.lol.service.system.intf.EventHandler;
import wsg.lol.service.system.intf.EventService;
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

    private EventService eventService;

    @Scheduled(fixedDelay = DateUtils.MILLIS_PER_MINUTE)
    public void addSummoners() {
        logger.info("Adding summoners by event.");
        Result handle = eventService.handle(EventTypeEnum.SummonerId, PageUtils.getRowBounds());
        systemService.sendWarnMessage(handle);
    }

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }
}
