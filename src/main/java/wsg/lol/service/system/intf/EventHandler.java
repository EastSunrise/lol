package wsg.lol.service.system.intf;

import wsg.lol.common.base.Result;

import java.util.List;

/**
 * Handler for events.
 *
 * @author Kingen
 */
public interface EventHandler {

    /**
     * handle of events
     *
     * @param contexts contexts of events
     */
    Result handle(List<String> contexts);
}
