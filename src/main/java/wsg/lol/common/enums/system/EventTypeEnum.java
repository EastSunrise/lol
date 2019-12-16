package wsg.lol.common.enums.system;

import wsg.lol.common.pojo.domain.system.EventDo;
import wsg.lol.common.pojo.domain.system.MatchEventDo;
import wsg.lol.common.pojo.domain.system.SummonerEventDo;
import wsg.lol.dao.mybatis.common.mapper.EventMapper;
import wsg.lol.dao.mybatis.mapper.region.event.MatchEventMapper;
import wsg.lol.dao.mybatis.mapper.region.event.SummonerEventMapper;
import wsg.lol.service.event.EventHandler;
import wsg.lol.service.event.MatchEventHandler;
import wsg.lol.service.event.SummonerEventHandler;

/**
 * Enum for types of events.
 *
 * @author Kingen
 */
public enum EventTypeEnum {
    Summoner(SummonerEventDo.class, SummonerEventHandler.class, SummonerEventMapper.class),
    Match(MatchEventDo.class, MatchEventHandler.class, MatchEventMapper.class);

    private Class<? extends EventDo> doClass;

    private Class<? extends EventHandler> handlerClass;

    private Class<? extends EventMapper<? extends EventDo>> mapperClass;

    EventTypeEnum(Class<? extends EventDo> doClass, Class<? extends EventHandler> handlerClass, Class<? extends EventMapper<? extends EventDo>> mapperClass) {
        this.doClass = doClass;
        this.handlerClass = handlerClass;
        this.mapperClass = mapperClass;
    }

    public Class<? extends EventDo> getDoClass() {
        return doClass;
    }

    public Class<? extends EventHandler> getHandlerClass() {
        return handlerClass;
    }

    public Class<? extends EventMapper<? extends EventDo>> getMapperClass() {
        return mapperClass;
    }
}
