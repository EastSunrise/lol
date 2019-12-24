package wsg.lol.dao.mybatis.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import wsg.lol.common.enums.system.RegionEnum;

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
                DatasourceHolder.getInstance().clearIdleDatasource();
            }
        }, 10000L, 60000L);
    }

    private Map<RegionEnum, DatasourceTimer> regionTimerMap = new HashMap<>();

    private DatasourceHolder() {
    }

    /*
     * Get an instance.
     */
    static DatasourceHolder getInstance() {
        return DatasourceHolderBuilder.instance;
    }

    /**
     * Add dynamic datasource.
     */
    synchronized void addDatasource(RegionEnum region, DataSource datasource) {
        DatasourceTimer datasourceTimer = new DatasourceTimer(datasource);
        regionTimerMap.put(region, datasourceTimer);
    }

    /**
     * Get dynamic datasource.
     */
    synchronized DataSource getDatasource(RegionEnum region) {
        if (regionTimerMap.containsKey(region)) {
            DatasourceTimer datasourceTimer = regionTimerMap.get(region);
            datasourceTimer.refreshTime();
            return datasourceTimer.getDatasource();
        }
        return null;
    }

    /**
     * Clear idle datasource.
     */
    private synchronized void clearIdleDatasource() {
        regionTimerMap.entrySet().removeIf(entry -> entry.getValue().checkAndClose());
    }

    /**
     * Builder for instance.
     */
    private static class DatasourceHolderBuilder {
        private static DatasourceHolder instance = new DatasourceHolder();
    }

    /**
     * Timer for the datasource.
     */
    private static class DatasourceTimer {

        /**
         * Max time for database to be free.
         */
        private static final long FREE_TIME = 600000L;

        /**
         * Dynamic datasource.
         */
        private DataSource datasource;

        /**
         * Last time accessing the database.
         */
        private long lastAccess;

        DatasourceTimer(DataSource datasource) {
            this.datasource = datasource;
            this.lastAccess = System.currentTimeMillis();
        }

        /**
         * Refresh the last time.
         */
        void refreshTime() {
            lastAccess = System.currentTimeMillis();
        }

        /**
         * Close if it's time out.
         *
         * @return True if closed.
         */
        boolean checkAndClose() {
            if (System.currentTimeMillis() - lastAccess > FREE_TIME) {
                datasource.close();
                return true;
            }
            return false;
        }

        DataSource getDatasource() {
            return datasource;
        }
    }
}