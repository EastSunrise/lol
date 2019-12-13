package wsg.lol.test;

import org.springframework.beans.factory.annotation.Autowired;
import wsg.lol.service.intf.EventService;
import wsg.lol.service.intf.MatchService;
import wsg.lol.service.intf.SummonerService;

/**
 * Test for match service.
 *
 * @author Kingen
 */
public class MatchServiceTest extends BaseTest {

    private MatchService matchService;
    private SummonerService summonerService;
    private EventService eventService;

    @Autowired
    public void setSummonerService(SummonerService summonerService) {
        this.summonerService = summonerService;
    }

    @Autowired
    public void setMatchService(MatchService matchService) {
        this.matchService = matchService;
    }
}