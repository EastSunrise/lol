package wsg.lol.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import wsg.lol.common.base.AppException;
import wsg.lol.common.constant.ErrorCodeConst;

import java.io.IOException;
import java.util.Objects;

/**
 * Loading yml files to the environment.
 *
 * @author Kingen
 */
@Configuration
public class YmlConfigurer {

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        try {
            yaml.setResources(new PathMatchingResourcePatternResolver().getResources("classpath:config/*.yml"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new AppException(ErrorCodeConst.SYSTEM_ERROR, "Can't load yml files from the classpath.");
        }
        configurer.setProperties(Objects.requireNonNull(yaml.getObject()));
        return configurer;
    }
}
