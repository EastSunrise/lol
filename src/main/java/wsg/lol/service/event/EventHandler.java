package wsg.lol.service.event;

import wsg.lol.common.base.Result;
import wsg.lol.common.pojo.domain.system.EventDo;

import java.util.List;

/**
 * Handler for events.
 *
 * @author Kingen
 */
public interface EventHandler {

    /**
     * Handle events.
     */
    Result handle(List<? extends EventDo> events);
}
