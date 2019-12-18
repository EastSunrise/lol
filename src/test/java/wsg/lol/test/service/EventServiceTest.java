package wsg.lol.test.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import tk.mybatis.mapper.entity.Example;
import wsg.lol.common.enums.system.EventStatusEnum;
import wsg.lol.common.enums.system.EventTypeEnum;
import wsg.lol.common.enums.system.RegionEnum;
import wsg.lol.common.pojo.domain.system.EventDo;
import wsg.lol.common.pojo.domain.system.MatchEventDo;
import wsg.lol.common.util.PageUtils;
import wsg.lol.config.ApiConfig;
import wsg.lol.dao.mybatis.common.mapper.EventMapper;
import wsg.lol.dao.mybatis.config.DatabaseIdentifier;
import wsg.lol.service.event.EventHandler;
import wsg.lol.service.intf.EventService;
import wsg.lol.test.base.BaseTest;

import java.util.HashMap;
import java.util.List;

/**
 * todo
 *
 * @author Kingen
 */
public class EventServiceTest extends BaseTest {

    @Qualifier(value = "SummonerEventHandler")
    @Autowired
    private EventHandler summonerEventHandler;

    @Qualifier(value = "MatchEventHandler")
    @Autowired
    private EventHandler matchEventHandler;

    @Autowired
    private EventMapper<MatchEventDo> matchEventMapper;

    @Autowired
    private EventService eventService;

    @Autowired
    private ApiConfig apiConfig;

    @Test
    public void handleSummoner() {
        EventDo eventDo = new EventDo();
        eventDo.setContext("mj-ROeahI8LXvU3eLRcOri_05QxncJp9Spz9x2vLzaCgAw");
        eventDo.setSource(apiConfig.getSingle().getUsername());
        eventDo.setStatus(EventStatusEnum.Unfinished);
        Assert.assertTrue(summonerEventHandler.handle(eventDo).isSuccess());
    }

    @Test
    public void handleMatch() {
        Example example = new Example(MatchEventDo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", EventStatusEnum.Unfinished);
        List<MatchEventDo> matchEventDos = matchEventMapper.selectByExampleAndRowBounds(example, PageUtils.DEFAULT_PAGE);
        for (MatchEventDo matchEventDo : matchEventDos) {
            matchEventHandler.handle(matchEventDo);
        }
    }

    @Test
    public void insertEvents() {
        DatabaseIdentifier.setPlatform(RegionEnum.KR);
        Assert.assertEquals(eventService.insertEvents(EventTypeEnum.Summoner, new HashMap<String, String>() {{
            put("mj-ROeahI8LXvU3eLRcOri_05QxncJp9Spz9x2vLzaCgAw", apiConfig.getSingle().getUsername());
        }}).getObject(), Integer.valueOf(1));
    }

    @Test
    public void updateStatus() {
    }
}