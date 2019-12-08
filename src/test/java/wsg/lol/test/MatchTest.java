package wsg.lol.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wsg.lol.LolApplication;
import wsg.lol.common.base.Result;
import wsg.lol.service.intf.MatchService;

import java.util.Date;

/**
 * todo
 *
 * @author Kingen
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        LolApplication.class
})
public class MatchTest {

    private MatchService matchService;

    @Test
    public void testUpdateMatches() {
        String accountId = "xDISCdDsN9hO-tHkLYBgQa7ZSDpzSz_JacDoaVTcfUPE6Q0";
        Result result = matchService.updateMatches(accountId, new Date(1574265600000L));
        System.out.println(result);
    }

    @Autowired
    public void setMatchService(MatchService matchService) {
        this.matchService = matchService;
    }
}
