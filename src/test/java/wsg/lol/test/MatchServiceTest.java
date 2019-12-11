package wsg.lol.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wsg.lol.common.base.Result;
import wsg.lol.common.enums.system.RegionEnum;
import wsg.lol.dao.mybatis.config.DatabaseIdentifier;
import wsg.lol.service.intf.EventService;
import wsg.lol.service.intf.MatchService;
import wsg.lol.service.intf.SummonerService;

import java.util.Date;

/**
 * Test for match service.
 *
 * @author Kingen
 */
public class MatchServiceTest extends BaseTest {

    private MatchService matchService;
    private SummonerService summonerService;
    private EventService eventService;

    @Test
    public void updateMatches() {
        DatabaseIdentifier.setPlatform(RegionEnum.NA);
        Result result = matchService.updateMatches(summoner.getAccountId(), new Date(1574265600000L));
        System.out.println(result);
    }

    @Autowired
    public void setSummonerService(SummonerService summonerService) {
        this.summonerService = summonerService;
    }

    @Autowired
    public void setMatchService(MatchService matchService) {
        this.matchService = matchService;
    }
}