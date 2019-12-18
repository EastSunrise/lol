package wsg.lol.test.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wsg.lol.common.base.ListResult;
import wsg.lol.common.pojo.dto.summoner.SummonerDto;
import wsg.lol.common.util.PageUtils;
import wsg.lol.service.intf.SummonerService;
import wsg.lol.test.base.BaseTest;

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
}