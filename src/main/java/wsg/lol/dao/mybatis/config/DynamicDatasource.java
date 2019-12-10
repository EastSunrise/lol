package wsg.lol.dao.mybatis.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wsg.lol.common.enums.system.PlatformRoutingEnum;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Define different database dynamic.
 *
 * @author Kingen
 */
public class DynamicDatasource extends DataSource {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDatasource.class);

    @Override
    public Connection getConnection() {
        PlatformRoutingEnum platform = DatabaseIdentifier.getPlatform();
        logger.info("Connecting to the database {}.", platform);
        DataSource datasource = DatasourceHolder.getInstance().getDatasource(platform);
        if (datasource == null) {
            try {
                datasource = getDatasource(platform);
                DatasourceHolder.getInstance().addDatasource(platform, datasource);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                logger.error("Failed to initialize the datasource of {}.", platform);
                return null;
            }
        }
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
    private DataSource getDatasource(PlatformRoutingEnum platform) throws IllegalArgumentException, IllegalAccessException {
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

        String url = String.format(datasource.getUrl(), platform.name().toLowerCase());
        datasource.setUrl(url);
        return datasource;
    }
}
