package wsg.lol.service.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wsg.lol.dao.mapper.EventMapper;
import wsg.lol.pojo.base.BaseResult;
import wsg.lol.pojo.event.SummonerEvent;
import wsg.lol.service.service.intf.EventService;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-21 16:17
 */
@Service("eventService")
public class EventServiceImpl implements EventService {

    private static Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);

    @Autowired
    private EventMapper eventMapper;

    @Override
    public BaseResult saveSummonerEvent(SummonerEvent summonerEvent) {
        if (1 != eventMapper.insertSummonerEvent(summonerEvent)) {
            logger.error("Fail to insert summoner event.");
            return BaseResult.fail("Fail to insert summoner event.");
        }
        return BaseResult.success();
    }
}
