package wsg.lol.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import tk.mybatis.mapper.entity.Example;
import wsg.lol.base.BaseTest;
import wsg.lol.common.enums.system.EventStatusEnum;
import wsg.lol.common.pojo.domain.system.MatchEventDo;
import wsg.lol.common.pojo.domain.system.SummonerEventDo;
import wsg.lol.common.util.PageUtils;
import wsg.lol.dao.mybatis.common.mapper.EventMapper;
import wsg.lol.service.event.EventHandler;
import wsg.lol.service.intf.EventService;

import java.util.List;

/**
 * Test for event service.
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
    private EventMapper<SummonerEventDo> summonerEventMapper;

    @Autowired
    private EventService eventService;

    @Test
    public void handleSummoner() {
        Example example = new Example(SummonerEventDo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", EventStatusEnum.Unfinished);
        List<SummonerEventDo> summonerEventDos = summonerEventMapper.selectByExampleAndRowBounds(example, PageUtils.SINGLE);
        for (SummonerEventDo summonerEventDo : summonerEventDos) {
            summonerEventHandler.handle(summonerEventDo);
        }
    }

    @Test
    public void handleMatch() {
        Example example = new Example(MatchEventDo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", EventStatusEnum.Unfinished);
        List<MatchEventDo> matchEventDos = matchEventMapper.selectByExampleAndRowBounds(example, PageUtils.SINGLE);
        for (MatchEventDo matchEventDo : matchEventDos) {
            matchEventHandler.handle(matchEventDo);
        }
    }

    @Test
    public void insertEvents() {
    }

    @Test
    public void updateStatus() {
    }
}