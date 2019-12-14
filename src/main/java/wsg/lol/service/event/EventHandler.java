package wsg.lol.service.event;

import wsg.lol.common.base.Result;
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
    Result handle(EventDo event);
}
