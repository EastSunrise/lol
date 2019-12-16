package wsg.lol.test.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wsg.lol.service.intf.LeagueService;
import wsg.lol.test.base.BaseTest;

/**
 * Test for league service.
 *
 * @author Kingen
 */
public class LeagueServiceTest extends BaseTest {

    @Autowired
    private LeagueService leagueService;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void initializeByLeagues() {
        Assert.assertTrue(leagueService.initializeByLeagues().isSuccess());
    }
}