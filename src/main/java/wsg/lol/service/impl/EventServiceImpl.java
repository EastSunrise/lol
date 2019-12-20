package wsg.lol.service.impl;

import org.apache.commons.collections.MapUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import wsg.lol.common.annotation.Performance;
import wsg.lol.common.base.ApiHTTPException;
import wsg.lol.common.base.AppException;
import wsg.lol.common.base.GenericResult;
import wsg.lol.common.base.Result;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.common.enums.system.EventStatusEnum;
import wsg.lol.common.enums.system.EventTypeEnum;
import wsg.lol.common.pojo.domain.system.EventDo;
import wsg.lol.common.util.PageUtils;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.dao.mybatis.common.mapper.EventMapper;
import wsg.lol.service.common.ServiceExecutor;
import wsg.lol.service.common.ServiceTask;
import wsg.lol.service.event.EventHandler;
import wsg.lol.service.intf.EventService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Kingen
 */
@Service
public class EventServiceImpl implements EventService {

    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

    private ApplicationContext applicationContext;

    @Override
    @Performance
    public GenericResult<Integer> handle(EventTypeEnum eventType, RowBounds rowBounds) {
        logger.info("Getting events of {}...", eventType);
        Example example = new Example(eventType.getDoClass());
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", EventStatusEnum.Unfinished);

        EventMapper<? extends EventDo> mapper = applicationContext.getBean(eventType.getMapperClass());
        List<? extends EventDo> events = ServiceExecutor.retryUntilNotEmpty((ServiceTask<EventDo>) () -> mapper.selectByExampleAndRowBounds(example, PageUtils.DEFAULT_PAGE)).getList();
        logger.info("Got {} events of {}. Handling...", events.size(), eventType);

        EventHandler eventHandler = applicationContext.getBean(eventType.getHandlerClass());
        int success = 0;
        for (EventDo event : events) {
            String context = event.getContext();
            try {
                eventHandler.handle(event).assertSuccess();
                success++;
            } catch (ApiHTTPException e) {
                logger.error("Failed to handle {} event of {}.", eventType, context);
                logger.error("{}: {}", e.getResponseCode().getMessage(), e.getUrl());
                updateStatus(eventType, context, EventStatusEnum.Unfinished, EventStatusEnum.Finishing).error(logger);
            } catch (Exception e) {
                logger.error("Failed to handle {} event of {}.", eventType, context);
                logger.error(e.getMessage(), e);
                updateStatus(eventType, context, EventStatusEnum.Unfinished, EventStatusEnum.Finishing).error(logger);
            }
        }

        logger.info("Events of {} done, {} succeeded, {} failed.", eventType, success, events.size() - success);
        return GenericResult.create(success);
    }

    @Override
    @Performance
    public GenericResult<Integer> insertEvents(EventTypeEnum eventType, Map<String, String> events) {
        if (MapUtils.isEmpty(events)) {
            logger.info("Events are empty.");
            return GenericResult.create(0);
        }

        List<EventDo> values = new ArrayList<>();
        for (Map.Entry<String, String> entry : events.entrySet()) {
            if (entry.getKey() != null) {
                EventDo eventDo = new EventDo();
                eventDo.setContext(entry.getKey());
                eventDo.setSource(entry.getValue());
                values.add(eventDo);
            }
        }

        EventMapper<? extends EventDo> mapper = applicationContext.getBean(eventType.getMapperClass());
        int count = mapper.insertIgnoreList(values, EventStatusEnum.Unfinished);
        logger.info("{} events inserted.", count);
        return GenericResult.create(count);
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
        int count = mapper.updateStatus(context.toString(), from, to);
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
