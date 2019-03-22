package wsg.lol.data.config;

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

    @Value("${data.region}")
    private ServiceProxyEnum region;

    @Value("${data.apiKey.path}")
    private String apiKeyPath;

    @Value("${data.language}")
    private String language;

    @Value("${data.globalProxy}")
    private RegionalProxyEnum globalProxy;

    @Value("${data.dataDir}")
    private String dataDir;

    @Value("${data.version}")
    private String latestVersion;

    public ServiceProxyEnum getRegion() {
        return region;
    }

    public String getApiKeyPath() {
        return apiKeyPath;
    }

    public String getLanguage() {
        return language;
    }

    public RegionalProxyEnum getGlobalProxy() {
        return globalProxy;
    }

    public String getDataDir() {
        return dataDir;
    }

    public String getLatestVersion() {
        return latestVersion;
    }
}
