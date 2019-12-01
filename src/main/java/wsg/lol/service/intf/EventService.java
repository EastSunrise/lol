package wsg.lol.service.intf;

import org.apache.ibatis.session.RowBounds;
import wsg.lol.common.base.Result;
import wsg.lol.common.enums.system.EventStatusEnum;
import wsg.lol.common.enums.system.EventTypeEnum;

import java.util.Set;

/**
 * Service for events.
 *
 * @author Kingen
 */
public interface EventService {

    /**
     * Handle events of the specified type by page.
     */
    Result handle(EventTypeEnum eventType, RowBounds rowBounds);

    /**
     * Insert events of specified type. Contexts are distinct.
     */
    Result insertEvents(EventTypeEnum eventType, Set<?> contexts);

    /**
     * Update the status of events.
     */
    Result updateStatus(EventTypeEnum eventType, Object context, EventStatusEnum from, EventStatusEnum to);
}
