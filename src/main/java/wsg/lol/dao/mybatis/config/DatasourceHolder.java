package wsg.lol.dao.mybatis.config;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.tomcat.jdbc.pool.DataSource;
import wsg.lol.common.enums.system.PlatformRoutingEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Manager.
 *
 * @author Kingen
 */
class DatasourceHolder {

    /**
     * Clear unused database by timer.
     */
    private static Timer clearIdleTask = new Timer();

    static {
        clearIdleTask.schedule(new TimerTask() {
            @Override
            public void run() {
                DatasourceHolder.instance().clearIdleDatasource();
            }
        }, 10000L, DateUtils.MILLIS_PER_MINUTE);
    }

    private Map<PlatformRoutingEnum, DatasourceTimer> platformTimerMap = new HashMap<>();

    private DatasourceHolder() {

    }

    /*
     * Get an instance.
     */
    static DatasourceHolder instance() {
        return DatasourceHolderBuilder.instance;
    }

    /**
     * Add dynamic datasource.
     */
    synchronized void addDatasource(PlatformRoutingEnum platform, DataSource datasource) {
        DatasourceTimer datasourceTimer = new DatasourceTimer(datasource);
        platformTimerMap.put(platform, datasourceTimer);
    }

    /**
     * Get dynamic datasource.
     */
    synchronized DataSource getDatasource(PlatformRoutingEnum platform) {
        if (platformTimerMap.containsKey(platform)) {
            DatasourceTimer datasourceTimer = platformTimerMap.get(platform);
            datasourceTimer.refreshTime();
            return datasourceTimer.getDatasource();
        }
        return null;
    }

    /**
     * Clear idle datasource.
     */
    private synchronized void clearIdleDatasource() {
        platformTimerMap.entrySet().removeIf(entry -> entry.getValue().checkAndClose());
    }

    /**
     * Builder for instance.
     */
    private static class DatasourceHolderBuilder {
        private static DatasourceHolder instance = new DatasourceHolder();
    }
}