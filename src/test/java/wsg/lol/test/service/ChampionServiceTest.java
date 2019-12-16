package wsg.lol.test.service;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import wsg.lol.common.base.AppException;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.service.intf.ChampionService;
import wsg.lol.test.base.BaseTest;

/**
 * Test for champion service.
 *
 * @author Kingen
 */
public class ChampionServiceTest extends BaseTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();
    @Autowired
    private ChampionService championService;

    @Test
    public void updateChampions() {
        Assert.assertTrue(championService.updateChampions("9.22.1").isSuccess());
        Assert.assertTrue(championService.updateChampions("9.23.1").isSuccess());
        exception.expect(AppException.class);
        exception.expectMessage(ErrorCodeConst.DRAGON_FILE_IO_ERROR);
        Assert.assertFalse(championService.updateChampions("9.24.1").isSuccess());
    }

    @Test
    public void updateSummonerSpells() {
        Assert.assertTrue(championService.updateSummonerSpells("9.22.1").isSuccess());
        Assert.assertTrue(championService.updateSummonerSpells("9.23.1").isSuccess());
        exception.expect(AppException.class);
        exception.expectMessage(ErrorCodeConst.DRAGON_FILE_IO_ERROR);
        Assert.assertFalse(championService.updateSummonerSpells("9.24.1").isSuccess());
    }


}