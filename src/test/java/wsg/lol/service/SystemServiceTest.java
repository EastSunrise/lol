package wsg.lol.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wsg.lol.base.BaseTest;
import wsg.lol.common.pojo.dto.system.VersionDto;
import wsg.lol.service.intf.SystemService;

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
        Assert.assertTrue(systemService.checkCdn("9.22.1"));
        Assert.assertTrue(systemService.checkCdn("9.23.1"));
        Assert.assertFalse(systemService.checkCdn("9.24.1"));
    }

    @Test
    public void getVersion() {
        VersionDto version = systemService.getVersion();
        Assert.assertEquals(version.getCurrentVersion(), "8.23.1");
        Assert.assertEquals(version.getLatestVersion(), "9.24.2");
        Assert.assertFalse(version.isLatestVersion());
    }

    @Test
    public void updateVersion() {
        systemService.updateVersion("8.23.1");
    }
}