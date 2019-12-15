package wsg.lol.test.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wsg.lol.service.intf.SystemService;
import wsg.lol.test.base.BaseTest;

/**
 * todo
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
        Assert.assertEquals(systemService.checkCdn("9.23.1").getObject(), true);
    }

    @Test
    public void getVersion() {
    }

    @Test
    public void updateVersion() {
    }

    @Test
    public void initialized() {
    }

    @Test
    public void initialize() {
    }
}