package wsg.lol.test.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wsg.lol.service.intf.EventService;
import wsg.lol.service.intf.MatchService;
import wsg.lol.test.base.BaseTest;

/**
 * Test for match service.
 *
 * @author Kingen
 */
public class MatchServiceTest extends BaseTest {

    @Autowired
    private MatchService matchService;

    @Autowired
    private EventService eventService;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void updateMatches() {
        Assert.assertTrue(matchService.updateMatches(summonerDto.getAccountId(), summonerDto.getLastMatch(), summonerDto.getEncryptUsername()).isSuccess());
    }

    @Test
    public void addMatch() {
        Assert.assertTrue(matchService.addMatch(214208007).isSuccess());
    }
}