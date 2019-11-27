package wsg.lol.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import wsg.lol.common.annotation.Performance;
import wsg.lol.common.base.AppException;
import wsg.lol.common.base.Result;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.common.enums.system.EventStatusEnum;
import wsg.lol.common.enums.system.EventTypeEnum;
import wsg.lol.common.pojo.dto.system.EventDto;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.dao.mybatis.mapper.system.EventMapper;
import wsg.lol.service.event.EventHandler;
import wsg.lol.service.intf.EventService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kingen
 */
@Service
public class EventServiceImpl implements EventService {

    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

    private EventMapper eventMapper;

    private ApplicationContext applicationContext;

    @Override
    @Performance
    public Result handle(EventTypeEnum eventType, RowBounds rowBounds) {
        logger.info("Getting {} events of {}.", rowBounds.getLimit(), eventType);
        EventDto cond = new EventDto();
        cond.setStatus(EventStatusEnum.Unfinished);
        cond.setType(eventType);
        List<EventDto> events = eventMapper.selectByRowBounds(cond, rowBounds);
        if (CollectionUtils.isEmpty(events)) {
            logger.info("No events got.");
            return ResultUtils.success();
        }

        logger.info("Handling events of {}", eventType);
        EventHandler eventHandler = (EventHandler) applicationContext.getBean(eventType.getEventBeanName());
        return eventHandler.handle(events);
    }

    @Override
    @Performance
    public Result insertEvents(EventTypeEnum eventType, List<?> contexts) {
        if (CollectionUtils.isEmpty(contexts)) {
            logger.info("Contexts are empty.");
            return ResultUtils.success();
        }

        List<String> values = new ArrayList<>();
        for (Object context : contexts) {
            if (context != null) {
                values.add(context.toString());
            }
        }
        int count = eventMapper.insertEventsOfType(eventType, EventStatusEnum.Unfinished, values);
        logger.info("{} events inserted.", count);
        return ResultUtils.success();
    }

    @Override
    @Performance
    public Result updateStatus(EventTypeEnum type, Object context, EventStatusEnum from, EventStatusEnum to) {
        if (context == null) {
            logger.info("Context is null, nothing updated.");
            return ResultUtils.success();
        }
        logger.info("Updating the status of the event {} to {}.", context, to);
        int count = eventMapper.updateStatus(type, context.toString(), from, to);
        if (count != 1) {
            logger.error("Failed to update the status of the event {} to {}.", context, to);
            throw new AppException(ErrorCodeConst.DATABASE_ERROR, "Failed to update the status of the event.");
        }
        return ResultUtils.success();
    }

    @Autowired
    public void setEventMapper(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
    }

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
