package wsg.lol.dao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import wsg.lol.pojo.enums.impl.others.RegionalProxyEnum;
import wsg.lol.pojo.enums.impl.others.ServiceProxyEnum;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-20 16:56
 */
@Component
@PropertySource(value = {"classpath:config/data-config.properties"})
public class Config {

    @Value("${api.region}")
    private ServiceProxyEnum region;

    @Value("${api.apiKey.path}")
    private String apiKeyPath;

    @Value("${api.globalProxy}")
    private RegionalProxyEnum globalProxy;

    public ServiceProxyEnum getRegion() {
        return region;
    }

    public String getApiKeyPath() {
        return apiKeyPath;
    }

    public RegionalProxyEnum getGlobalProxy() {
        return globalProxy;
    }
}
