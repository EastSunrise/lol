package wsg.lol.service.intf;

import org.apache.ibatis.session.RowBounds;
import wsg.lol.common.base.GenericResult;
import wsg.lol.common.base.Result;
import wsg.lol.common.enums.system.EventStatusEnum;
import wsg.lol.common.enums.system.EventTypeEnum;

import java.util.Map;

/**
 * Service for events.
 *
 * @author Kingen
 */
public interface EventService {

    /**
     * Handle events of the specified type by page.
     */
    GenericResult<Integer> handle(EventTypeEnum eventType, RowBounds rowBounds);

    /**
     * Insert events of specified type. Contexts are distinct.
     *
     * @param events context-source map
     * @return successful count
     */
    GenericResult<Integer> insertEvents(EventTypeEnum eventType, Map<String, String> events);

    /**
     * Update the status of the event.
     */
    Result updateStatus(EventTypeEnum eventType, Object context, EventStatusEnum from, EventStatusEnum to);
}
