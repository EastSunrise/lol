package wsg.lol.dao.mybatis.config;

import wsg.lol.common.enums.system.PlatformRoutingEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * Manager for different databases.
 *
 * @author Kingen
 */
class DatabaseManager {

    private Map<PlatformRoutingEnum, String> platformDatabaseMap = new HashMap<>();

    private DatabaseManager() {
        for (PlatformRoutingEnum value : PlatformRoutingEnum.values()) {
            platformDatabaseMap.put(value, value.name().toLowerCase());

        }
    }

    static DatabaseManager instance() {
        return DatabaseManagerBuilder.instance;
    }

    String getDatabaseName(PlatformRoutingEnum platform) {
        if (platformDatabaseMap.containsKey(platform)) {
            return platformDatabaseMap.get(platform);
        }
        return platformDatabaseMap.get(PlatformRoutingEnum.LOL);
    }

    private static class DatabaseManagerBuilder {
        private static DatabaseManager instance = new DatabaseManager();
    }
}
