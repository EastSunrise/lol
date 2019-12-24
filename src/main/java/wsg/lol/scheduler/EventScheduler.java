package wsg.lol.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
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

    private EventService eventService;

    /**
     * Add summoners by handling events of type {@link EventTypeEnum#Summoner}.
     * Request 4.
     */
    @Scheduled(fixedDelay = 1)
    public void handleSummoners() {
        eventService.handle(EventTypeEnum.Summoner, PageUtils.DEFAULT_PAGE);
    }

    /**
     * Add matches by handling events of type {@link EventTypeEnum#Match}
     * Request 2.
     * <p>
     * Meanwhile, add events of type {@link EventTypeEnum#Summoner} if not exist from the participants of matches.
     */
    @Scheduled(fixedDelay = 1)
    public void handleMatches() {
        eventService.handle(EventTypeEnum.Match, PageUtils.DEFAULT_PAGE);
    }

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }
}
