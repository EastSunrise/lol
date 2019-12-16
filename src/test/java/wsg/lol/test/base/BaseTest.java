package wsg.lol.test.base;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wsg.lol.common.pojo.dto.summoner.SummonerDto;
import wsg.lol.config.GlobalConfig;
import wsg.lol.service.intf.SummonerService;

/**
 * Base test.
 *
 * @author Kingen
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BaseTest implements InitializingBean {

    protected String summonerName;

    protected SummonerDto summonerDto;

    @Autowired
    private GlobalConfig globalConfig;

    @Autowired
    private SummonerService summonerService;

    @Override
    public void afterPropertiesSet() {
        switch (globalConfig.getRegion()) {
            case JP:
                summonerName = "isurugi";
                break;
            case KR:
                summonerName = "Hide on bush";
            default:
                summonerName = "Kingen439" + globalConfig.getRegion().ordinal();
        }

        summonerDto = summonerService.getSummonersByName(summonerName).getObject();
    }
}
