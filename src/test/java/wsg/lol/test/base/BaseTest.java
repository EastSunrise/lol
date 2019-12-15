package wsg.lol.test.base;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wsg.lol.common.enums.system.RegionEnum;
import wsg.lol.common.pojo.dto.summoner.SummonerDto;
import wsg.lol.config.GlobalConfig;
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
    private GlobalConfig globalConfig;
    private SummonerV4 summonerV4;

    protected final String version = "9.24.1";

    @Override
    public void afterPropertiesSet() {
        DatabaseIdentifier.setPlatform(RegionEnum.NA);
        String name = "Kingen439";
        if (globalConfig.getRegion().equals(RegionEnum.KR)) {
            name = "Hide on bush";
        }
        name += globalConfig.getRegion().ordinal();
        summoner = summonerV4.getSummoner(name, SummonerV4.CondKeyEnum.NAME);
    }

    @Autowired
    public void setGlobalConfig(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
    }

    @Autowired
    public void setSummonerV4(SummonerV4 summonerV4) {
        this.summonerV4 = summonerV4;
    }
}
