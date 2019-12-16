package wsg.lol.test.service;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import wsg.lol.service.intf.SharedService;
import wsg.lol.test.base.BaseTest;

/**
 * Test for shared service.
 *
 * @author Kingen
 */
public class SharedServiceTest extends BaseTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();
    @Autowired
    private SharedService sharedService;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getFeaturedGames() {
        System.out.println("Get first.");
        Assert.assertNotNull(sharedService.getFeaturedGames());
        System.out.println("Get second.");
        Assert.assertNotNull(sharedService.getFeaturedGames());
        System.out.println("Update.");
        sharedService.updateFeaturedGames();
        System.out.println("Get third.");
        Assert.assertNotNull(sharedService.getFeaturedGames());
    }

    @Test
    public void getShardStatus() {
        System.out.println("Get first.");
        Assert.assertNotNull(sharedService.getShardStatus());
        System.out.println("Get second.");
        Assert.assertNotNull(sharedService.getShardStatus());
        System.out.println("Update.");
        sharedService.updateShardStatus();
        System.out.println("Get third.");
        Assert.assertNotNull(sharedService.getShardStatus());
    }

    @Test
    public void getChampionRotation() {
        System.out.println("Get first.");
        Assert.assertNotNull(sharedService.getChampionRotation());
        System.out.println("Get second.");
        Assert.assertNotNull(sharedService.getChampionRotation());
        System.out.println("Update.");
        sharedService.updateChampionRotation();
        System.out.println("Get third.");
        Assert.assertNotNull(sharedService.getChampionRotation());
    }
}