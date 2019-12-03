package wsg.lol.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import wsg.lol.common.annotation.Performance;
import wsg.lol.common.base.AppException;
import wsg.lol.common.base.GenericResult;
import wsg.lol.common.base.Result;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.common.enums.system.EventStatusEnum;
import wsg.lol.common.enums.system.EventTypeEnum;
import wsg.lol.common.pojo.domain.system.EventDo;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.dao.mybatis.common.EventMapper;
import wsg.lol.service.event.EventHandler;
import wsg.lol.service.intf.EventService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Kingen
 */
@Service
public class EventServiceImpl implements EventService {

    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

    private ApplicationContext applicationContext;

    @Override
    @Performance
    public Result handle(EventTypeEnum eventType, RowBounds rowBounds) {
        logger.info("Getting events of {}...", eventType);
        Example example = new Example(eventType.getDoClass());
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", EventStatusEnum.Unfinished);

        EventMapper<? extends EventDo> mapper = applicationContext.getBean(eventType.getMapperClass());
        List<? extends EventDo> events = mapper.selectByExampleAndRowBounds(example, rowBounds);
        if (CollectionUtils.isEmpty(events)) {
            logger.info("No events got.");
            return ResultUtils.success();
        }
        logger.info("Got {} events of {}. Handling...", events.size(), eventType);

        EventHandler eventHandler = applicationContext.getBean(eventType.getHandlerClass());
        return eventHandler.handle(events);
    }

    @Override
    @Performance
    public GenericResult<Integer> insertEvents(EventTypeEnum eventType, Set<?> contexts) {
        GenericResult<Integer> result = new GenericResult<>();
        if (CollectionUtils.isEmpty(contexts)) {
            logger.info("Contexts are empty.");
            result.setObject(0);
            return result;
        }

        List<String> values = new ArrayList<>();
        for (Object context : contexts) {
            if (context != null) {
                values.add(context.toString());
            }
        }

        EventMapper<? extends EventDo> mapper = applicationContext.getBean(eventType.getMapperClass());
        int count = mapper.insertIgnoreList(values, EventStatusEnum.Unfinished);
        logger.info("{} events inserted.", count);
        result.setObject(count);
        return result;
    }

    @Override
    @Performance
    public Result updateStatus(EventTypeEnum eventType, Object context, EventStatusEnum from, EventStatusEnum to) {
        if (context == null) {
            logger.info("Context is null, nothing updated.");
            return ResultUtils.success();
        }
        logger.info("Updating the status of the event {} to {}.", context, to);
        EventMapper<? extends EventDo> mapper = applicationContext.getBean(eventType.getMapperClass());
        int count = mapper.updateStatus(context.toString(), EventStatusEnum.Unfinished, EventStatusEnum.Finished);
        if (count != 1) {
            logger.error("Failed to update the status of the event {} to {}.", context, to);
            throw new AppException(ErrorCodeConst.DATABASE_ERROR, "Failed to update the status of the event.");
        }
        return ResultUtils.success();
    }

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
