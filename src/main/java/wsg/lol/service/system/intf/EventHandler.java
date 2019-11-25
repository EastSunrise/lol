package wsg.lol.service.system.intf;

import wsg.lol.common.base.Result;
import wsg.lol.common.pojo.dto.system.EventDto;

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
    Result handle(List<EventDto> events);
}
