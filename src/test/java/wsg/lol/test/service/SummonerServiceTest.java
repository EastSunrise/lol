package wsg.lol.test.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import tk.mybatis.mapper.entity.Example;
import wsg.lol.common.base.ListResult;
import wsg.lol.common.constant.ConfigConst;
import wsg.lol.common.enums.system.EventStatusEnum;
import wsg.lol.common.enums.system.EventTypeEnum;
import wsg.lol.common.pojo.domain.system.MatchEventDo;
import wsg.lol.common.pojo.domain.system.SummonerEventDo;
import wsg.lol.common.pojo.dto.summoner.SummonerDto;
import wsg.lol.common.util.PageUtils;
import wsg.lol.dao.api.client.ApiClient;
import wsg.lol.dao.mybatis.common.mapper.EventMapper;
import wsg.lol.service.event.EventHandler;
import wsg.lol.service.intf.EventService;
import wsg.lol.service.intf.SummonerService;
import wsg.lol.test.base.BaseTest;

import java.util.HashMap;
import java.util.List;

/**
 * Test for summoner service.
 *
 * @author Kingen
 */
public class SummonerServiceTest extends BaseTest {

    @Autowired
    private SummonerService summonerService;

    @Autowired
    private EventService eventService;

    @Autowired
    private ApiClient apiClient;

    @Autowired
    private EventMapper<SummonerEventDo> summonerEventMapper;

    @Qualifier(value = "SummonerEventHandler")
    @Autowired
    private EventHandler summonerEventHandler;

    @Qualifier(value = "MatchEventHandler")
    @Autowired
    private EventHandler matchEventHandler;

    @Autowired
    private EventMapper<MatchEventDo> matchEventMapper;

    @Before
    public void setUp() {
    }

    @Test
    public void updateSummoner() {
        ListResult<SummonerDto> summonersForUpdate = summonerService.getSummonersForUpdate(PageUtils.SINGLE);
        for (SummonerDto summoner : summonersForUpdate.getList()) {
            Assert.assertTrue(summonerService.updateSummoner(summoner.getId(), summoner.getEncryptUsername()).isSuccess());
        }
    }

    @Test
    public void updateMatches() {
        ListResult<SummonerDto> summonersForMatch = summonerService.getSummonersForMatch(PageUtils.SINGLE);
        for (SummonerDto summoner : summonersForMatch.getList()) {
            Assert.assertTrue(summonerService.updateMatches(summoner.getAccountId(), summoner.getLastMatch(), summoner.getEncryptUsername()).isSuccess());
        }
    }

    @Test
    public void routine() {
        SummonerDto summoner = new SummonerDto();
        String summonerId = summoner.getId();

        Assert.assertEquals(eventService.insertEvents(EventTypeEnum.Summoner, new HashMap<String, String>() {{
            put(summonerId, apiClient.getUsername());
        }}).getObject(), Integer.valueOf(1));

        Example example = new Example(SummonerEventDo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", EventStatusEnum.Unfinished);
        criteria.andEqualTo("context", summonerId);
        List<SummonerEventDo> summonerEventDos = summonerEventMapper.selectByExampleAndRowBounds(example, PageUtils.SINGLE);
        for (SummonerEventDo summonerEventDo : summonerEventDos) {
            summonerEventHandler.handle(summonerEventDo);
        }

        Assert.assertTrue(summonerService.updateMatches(summoner.getAccountId(), ConfigConst.MATCH_BEGIN_DATE, apiClient.getUsername()).isSuccess());

        example = new Example(MatchEventDo.class);
        criteria = example.createCriteria();
        criteria.andEqualTo("status", EventStatusEnum.Unfinished);
        criteria.andEqualTo("source", summoner.getAccountId());
        List<MatchEventDo> matchEventDos = matchEventMapper.selectByExampleAndRowBounds(example, PageUtils.DEFAULT_PAGE);
        for (MatchEventDo matchEventDo : matchEventDos) {
            try {
                matchEventHandler.handle(matchEventDo);
            } catch (Exception e) {
                eventService.updateStatus(EventTypeEnum.Match, matchEventDo.getContext(), EventStatusEnum.Unfinished, EventStatusEnum.Finishing).assertSuccess();
            }
        }
    }
}