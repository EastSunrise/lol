package wsg.lol.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wsg.lol.service.intf.ChampionService;
import wsg.lol.test.base.BaseTest;

/**
 * todo
 *
 * @author Kingen
 */
public class ChampionServiceTest extends BaseTest {

    private static final String version = "9.24.1";
    @Autowired
    private ChampionService championService;

    @Test
    public void updateChampions() {
        championService.updateChampions(version);
    }

    @Test
    public void updateSummonerSpells() {
    }


}