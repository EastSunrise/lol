package wsg.lol.dao.mybatis.config;

import wsg.lol.common.enums.route.PlatformRoutingEnum;

/**
 * Identifier for different databases.
 *
 * @author Kingen
 */
public class DatabaseIdentifier {

    private static ThreadLocal<PlatformRoutingEnum> platform = new ThreadLocal<>();

    public static PlatformRoutingEnum getPlatform() {
        return platform.get();
    }

    public static void setPlatform(PlatformRoutingEnum routingEnum) {
        platform.set(routingEnum);
    }

    public static String getDatabaseName(PlatformRoutingEnum platform) {
        return platform.name().toLowerCase();
    }
}
