package wsg.lol.service.event;

import wsg.lol.common.pojo.domain.system.EventDo;

/**
 * Handler for events.
 *
 * @author Kingen
 */
public interface EventHandler {

    /**
     * Handle an event.
     */
    void handle(EventDo event);
}
