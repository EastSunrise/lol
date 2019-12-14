package wsg.lol.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import wsg.lol.common.base.Result;
import wsg.lol.common.enums.system.EventTypeEnum;
import wsg.lol.common.util.PageUtils;
import wsg.lol.service.intf.EventService;

/**
 * Scheduler for events.
 *
 * @author Kingen
 */
@Component
public class EventScheduler {

    private static final Logger logger = LoggerFactory.getLogger(EventScheduler.class);

    private EventService eventService;

    /**
     * Add summoners by handling events of type {@link EventTypeEnum#Summoner}.
     * Request 4.
     */
    @Scheduled(cron = "${cron.event.summoner}")
    public void handleSummoners() {
        Result result = eventService.handle(EventTypeEnum.Summoner, PageUtils.getRowBounds());
        result.error(logger);
    }

    /**
     * Add matches by handling events of type {@link EventTypeEnum#Match}
     * Request 2.
     * <p>
     * Meanwhile, add events of type {@link EventTypeEnum#Summoner} if not exist from the participants of matches.
     */
    @Scheduled(cron = "${cron.event.match}")
    public void handleMatches() {
        Result result = eventService.handle(EventTypeEnum.Match, PageUtils.getRowBounds());
        result.error(logger);
    }

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }
}
