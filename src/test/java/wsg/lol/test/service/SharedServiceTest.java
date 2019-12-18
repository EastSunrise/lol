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
        testShared(v -> sharedService.getFeaturedGames(), v -> {
            sharedService.updateFeaturedGames();
            return null;
        });
    }

    @Test
    public void getShardStatus() {
        testShared(v -> sharedService.getShardStatus(), v -> {
            sharedService.updateShardStatus();
            return null;
        });
    }

    @Test
    public void getChampionRotation() {
        testShared(aVoid -> sharedService.getChampionRotation(), aVoid -> {
            sharedService.updateChampionRotation();
            return null;
        });
    }

    protected void testShared(Task<Void> getTask, Task<Void> updateTask) {
        System.out.println("Get first.");
        Assert.assertNotNull(getTask.run(null));
        System.out.println("Get second.");
        Assert.assertNotNull(getTask.run(null));
        System.out.println("Update.");
        updateTask.run(null);
        System.out.println("Get third.");
        Assert.assertNotNull(getTask.run(null));
    }
}