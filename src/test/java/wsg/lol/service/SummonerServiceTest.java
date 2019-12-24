package wsg.lol.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wsg.lol.base.BaseTest;
import wsg.lol.common.pojo.dto.summoner.SummonerDto;
import wsg.lol.common.util.PageUtils;
import wsg.lol.service.intf.SummonerService;

import java.util.List;

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
        List<SummonerDto> summonersForUpdate = summonerService.getSummonersForUpdate(PageUtils.SINGLE);
        for (SummonerDto summoner : summonersForUpdate) {
            summonerService.updateSummoner(summoner.getId());
        }
    }

    @Test
    public void updateMatches() {
        List<SummonerDto> summonersForMatch = summonerService.getSummonersForMatch(PageUtils.SINGLE);
        for (SummonerDto summoner : summonersForMatch) {
            summonerService.updateMatches(summoner.getAccountId(), summoner.getLastMatch());
        }
    }
}