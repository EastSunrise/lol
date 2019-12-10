package wsg.lol.dao.mybatis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import wsg.lol.common.enums.system.RegionEnum;
import wsg.lol.config.GlobalConfig;

/**
 * Identifier for different databases.
 *
 * @author Kingen
 */
@Configuration
public class DatabaseIdentifier {

    private static GlobalConfig globalConfig;

    private static ThreadLocal<RegionEnum> threadLocal = new ThreadLocal<>();

    public static RegionEnum getRegion() {
        RegionEnum region = threadLocal.get();
        return region == null ? globalConfig.getRegion() : region;
    }

    public static void setPlatform(RegionEnum region) {
        threadLocal.set(region == null ? globalConfig.getRegion() : region);
    }

    @Autowired
    public void setGlobalConfig(GlobalConfig globalConfig) {
        DatabaseIdentifier.globalConfig = globalConfig;
    }
}
