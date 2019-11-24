package wsg.lol.dao.mybatis.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import wsg.lol.common.enums.route.PlatformRoutingEnum;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * To define different database dynamic.
 *
 * @author Kingen
 */
public class DynamicDatasource extends DataSource {

    private static Logger logger = LogManager.getLogger(DynamicDatasource.class);

    @Override
    public Connection getConnection() {
        PlatformRoutingEnum platform = DatabaseIdentifier.getPlatform();

        DataSource datasource = DatasourceHolder.instance().getDatasource(platform);

        if (datasource == null) {
            try {
                datasource = initializeDatasource(platform);
                DatasourceHolder.instance().addDatasource(platform, datasource);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                logger.error("Failed to initialize the datasource of {}.", platform);
                return null;
            }
        }

        datasource = DatasourceHolder.instance().getDatasource(platform);
        try {
            return datasource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get datasource by formatting the url with the name of database.
     */
    private DataSource initializeDatasource(PlatformRoutingEnum platform) throws IllegalArgumentException, IllegalAccessException {
        DataSource datasource = new DataSource();

        PoolProperties property = new PoolProperties();
        Field[] fields = PoolProperties.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(this.getPoolProperties());

            try {
                field.set(property, value);
            } catch (Exception e) {
                logger.info("Set value fail. attr name:" + field.getName());
            }
        }
        datasource.setPoolProperties(property);

        String urlFormat = this.getUrl();
        String url = String.format(urlFormat, DatabaseManager.instance().getDatabaseName(platform));
        datasource.setUrl(url);
        return datasource;
    }
}
