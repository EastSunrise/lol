package wsg.lol.dao.mybatis.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

/**
 * Configuration for different data sources.
 *
 * @author Kingen
 */
@Configuration
@MapperScan(basePackages = {
        "wsg.lol.dao.mybatis.mapper.lol", "wsg.lol.dao.mybatis.mapper.region"
}, value = "sqlSessionFactory")
public class DatasourceConfig {

    /**
     * Get datasource by args.
     */
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource datasource() {
        DataSourceBuilder<?> builder = DataSourceBuilder.create();
        DataSourceBuilder<DynamicDatasource> sourceBuilder = builder.type(DynamicDatasource.class);
        return sourceBuilder.build();
    }

    /**
     * Create sql session factory.
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory(@Qualifier("dataSource") DataSource datasource) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        try {
            bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/*/*/*Mapper.xml"));
            bean.setTypeHandlersPackage("wsg.lol.dao.mybatis.typehandler");
            org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
            configuration.setMapUnderscoreToCamelCase(true);
            bean.setConfiguration(configuration);
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager getTransactionManager(@Qualifier("dataSource") DataSource datasource) {
        return new DataSourceTransactionManager(datasource);
    }

    @Bean(name = "transactionTemplate")
    public TransactionTemplate getTransactionTemplate(@Qualifier("transactionManager") DataSourceTransactionManager transactionManager) {
        return new TransactionTemplate(transactionManager);
    }
}
