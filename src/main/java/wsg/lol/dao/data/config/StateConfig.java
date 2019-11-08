package wsg.lol.dao.data.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * wsg
 *
 * @author EastSunrise
 */
@Component
@PropertySource(value = {"classpath:config/data-config.properties"})
public class StateConfig {

    @Value("${state.dataDir}")
    private String dataDir;

    @Value("${state.language}")
    private String language;

    @Value("${state.versionPath}")
    private String versionPath;

    @Value("${state.url.cdn}")
    private String cdnUrl;

    public String getDataDir() {
        return dataDir;
    }

    public String getLanguage() {
        return language;
    }

    public String getVersionPath() {
        return versionPath;
    }

    public String getCdnUrl() {
        return cdnUrl;
    }
}
