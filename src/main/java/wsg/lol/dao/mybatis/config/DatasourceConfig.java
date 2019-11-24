package wsg.lol.dao.mybatis.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * Configuration for different data sources.
 *
 * @author Kingen
 */
//@Configuration
//@MapperScan(basePackages = "wsg.lol.dao.mybatis.mapper", value = "sqlSessionFactory")
public class DatasourceConfig {

    /**
     * Get datasource by args.
     */
    @Bean(name = "datasource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource LOLDataSource() {
        DataSourceBuilder<?> builder = DataSourceBuilder.create();
        builder.type(DynamicDatasource.class);
        return builder.build();
    }

    /**
     * Create sql session factory.
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory(@Qualifier("datasource") DataSource datasource) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        try {
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
