package wsg.lol.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wsg.lol.common.enums.system.RegionEnum;
import wsg.lol.common.pojo.dto.summoner.SummonerDto;
import wsg.lol.dao.api.impl.SummonerV4;
import wsg.lol.dao.mybatis.config.DatabaseIdentifier;

/**
 * Base test.
 *
 * @author Kingen
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BaseTest implements InitializingBean {

    protected SummonerDto summoner;
    private SummonerV4 summonerV4;

    @Override
    public void afterPropertiesSet() {
        DatabaseIdentifier.setPlatform(RegionEnum.NA);
        summoner = summonerV4.getSummoner("Kingen4395", SummonerV4.CondKeyEnum.NAME);
    }

    @Autowired
    public void setSummonerV4(SummonerV4 summonerV4) {
        this.summonerV4 = summonerV4;
    }
}
