package wsg.lol.test.service;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import wsg.lol.common.base.AppException;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.service.intf.ItemService;
import wsg.lol.test.base.BaseTest;

/**
 * Test for item service.
 *
 * @author Kingen
 */
public class ItemServiceTest extends BaseTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();
    @Autowired
    private ItemService itemService;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void updateItems() {
        Assert.assertTrue(itemService.updateItems("9.22.1").isSuccess());
        Assert.assertTrue(itemService.updateItems("9.23.1").isSuccess());
        exception.expect(AppException.class);
        exception.expectMessage(ErrorCodeConst.DRAGON_FILE_IO_ERROR);
        Assert.assertFalse(itemService.updateItems("9.24.1").isSuccess());
    }
}