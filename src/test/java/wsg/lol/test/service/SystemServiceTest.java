package wsg.lol.test.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wsg.lol.common.result.system.VersionResult;
import wsg.lol.service.intf.SystemService;
import wsg.lol.test.base.BaseTest;

/**
 * Test for system service.
 *
 * @author Kingen
 */
public class SystemServiceTest extends BaseTest {

    @Autowired
    private SystemService systemService;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void checkCdn() {
        Assert.assertEquals(systemService.checkCdn("9.22.1").getObject(), true);
        Assert.assertEquals(systemService.checkCdn("9.23.1").getObject(), true);
        Assert.assertEquals(systemService.checkCdn("9.24.1").getObject(), false);
    }

    @Test
    public void getVersion() {
        VersionResult version = systemService.getVersion();
        Assert.assertEquals(version.getCurrentVersion(), "8.23.1");
        Assert.assertEquals(version.getLatestVersion(), "9.24.2");
        Assert.assertFalse(version.isLatestVersion());
    }

    @Test
    public void updateVersion() {
        Assert.assertTrue(systemService.updateVersion("8.23.1").isSuccess());
    }
}