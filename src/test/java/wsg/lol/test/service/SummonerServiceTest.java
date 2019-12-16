package wsg.lol.test.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wsg.lol.common.base.Result;
import wsg.lol.common.constant.ConfigConst;
import wsg.lol.common.util.PageUtils;
import wsg.lol.service.intf.SummonerService;
import wsg.lol.test.base.BaseTest;

import java.util.Calendar;

/**
 * Test for summoner service.
 *
 * @author Kingen
 */
public class SummonerServiceTest extends BaseTest {

    @Autowired
    private SummonerService summonerService;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        summonerService.updateSummonerLastMatch(summonerDto.getAccountId(), ConfigConst.MATCH_BEGIN_DATE);
    }

    @Test
    public void addSummoner() {
        Result result = summonerService.addSummoner(summonerName);
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void updateSummoner() {
        Assert.assertTrue(summonerService.updateSummoner(summonerDto.getId(), "Kingen4399").isSuccess());
    }

    @Test
    public void updateSummonerLastMatch() {
        Assert.assertTrue(summonerService.updateSummonerLastMatch(summonerDto.getAccountId(), Calendar.getInstance().getTime()).isSuccess());
    }

    @Test
    public void updateSummonerLastUpdate() {
        Assert.assertTrue(summonerService.updateSummonerLastUpdate(summonerDto.getId(), Calendar.getInstance().getTime()).isSuccess());
    }

    @Test
    public void getSummonersForUpdate() {
        Assert.assertTrue(summonerService.getSummonersForUpdate(PageUtils.getRowBounds()).isSuccess());
    }

    @Test
    public void getSummonersForMatch() {
        Assert.assertTrue(summonerService.getSummonersForMatch(PageUtils.getRowBounds()).isSuccess());
    }

    @Test
    public void getSummonersByName() {
        Assert.assertTrue(summonerService.getSummonersByName(summonerName).isSuccess());
    }
}