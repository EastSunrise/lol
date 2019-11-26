package wsg.lol.service.system.intf;

import org.apache.ibatis.session.RowBounds;
import wsg.lol.common.base.Result;
import wsg.lol.common.enums.system.EventStatusEnum;
import wsg.lol.common.enums.system.EventTypeEnum;

import java.util.List;

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
     * Insert list of events of specified type.
     */
    Result insertEvents(EventTypeEnum eventType, List<?> contexts);

    /**
     * Update the status of events.
     */
    Result updateStatus(EventTypeEnum type, Object context, EventStatusEnum from, EventStatusEnum to);
}
