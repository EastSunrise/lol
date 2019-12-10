package wsg.lol.dao.mybatis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import wsg.lol.common.enums.system.PlatformRoutingEnum;
import wsg.lol.config.GlobalConfig;

/**
 * Identifier for different databases.
 *
 * @author Kingen
 */
@Configuration
public class DatabaseIdentifier {

    private static GlobalConfig globalConfig;

    private static ThreadLocal<PlatformRoutingEnum> threadLocal = new ThreadLocal<>();

    public static PlatformRoutingEnum getPlatform() {
        PlatformRoutingEnum platform = threadLocal.get();
        return platform == null ? globalConfig.getRegion() : platform;
    }

    public static void setPlatform(PlatformRoutingEnum platform) {
        threadLocal.set(platform == null ? globalConfig.getRegion() : platform);
    }

    @Autowired
    public void setGlobalConfig(GlobalConfig globalConfig) {
        DatabaseIdentifier.globalConfig = globalConfig;
    }
}
