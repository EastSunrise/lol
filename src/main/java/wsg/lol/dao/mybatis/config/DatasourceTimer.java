package wsg.lol.dao.mybatis.config;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.tomcat.jdbc.pool.DataSource;

/**
 * Manager for timer.
 *
 * @author Kingen
 */
class DatasourceTimer {

    /**
     * Max time for database to be free.
     */
    private static final long FREE_TIME = 10 * DateUtils.MILLIS_PER_MINUTE;

    /**
     * Dynamic datasource.
     */
    private DataSource datasource;

    /**
     * Last time accessing the database.
     */
    private long lastAccessTime;

    DatasourceTimer(DataSource datasource) {
        this.datasource = datasource;
        this.lastAccessTime = System.currentTimeMillis();
    }

    /**
     * Refresh the last time.
     */
    void refreshTime() {
        lastAccessTime = System.currentTimeMillis();
    }

    /**
     * Close if it's time out.
     *
     * @return True if closed.
     */
    boolean checkAndClose() {
        if (System.currentTimeMillis() - lastAccessTime > FREE_TIME) {
            datasource.close();
            return true;
        }

        return false;
    }

    DataSource getDatasource() {
        return datasource;
    }
}
