package wsg.lol.service;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import wsg.lol.base.BaseTest;
import wsg.lol.service.intf.SharedService;

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
        testShared(() -> sharedService.getFeaturedGames(), () -> sharedService.updateFeaturedGames());
    }

    @Test
    public void getShardStatus() {
        testShared(() -> sharedService.getShardStatus(), () -> sharedService.updateShardStatus());
    }

    @Test
    public void getChampionRotation() {
        testShared(() -> sharedService.getChampionRotation(), () -> sharedService.updateChampionRotation());
    }

    protected void testShared(NoneArgsTask getTask, NoneTask updateTask) {
        System.out.println("Get first.");
        Assert.assertNotNull(getTask.run());
        System.out.println("Get second.");
        Assert.assertNotNull(getTask.run());
        System.out.println("Update.");
        updateTask.run();
        System.out.println("Get third.");
        Assert.assertNotNull(getTask.run());
    }
}