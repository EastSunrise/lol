package wsg.lol.dao.mybatis.config;

import wsg.lol.common.enums.system.PlatformRoutingEnum;

/**
 * Identifier for different databases.
 *
 * @author Kingen
 */
public class DatabaseIdentifier {

    private static ThreadLocal<PlatformRoutingEnum> threadLocal = new ThreadLocal<>();

    public static PlatformRoutingEnum getPlatform() {
        return threadLocal.get();
    }

    public static void setPlatform(PlatformRoutingEnum platform) {
        threadLocal.set(platform);
    }
}
