package wsg.lol.dao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * wsg
 *
 * @author wangsigen
 */
@Component
@PropertySource(value = {"classpath:config/data-config.properties"})
public class FileConfig {

    @Value("${file.dataDir}")
    private String dataDir;

    @Value("${file.version}")
    private String latestVersion;

    @Value("${file.language}")
    private String language;

    public String getDataDir() {
        return dataDir;
    }

    public String getLatestVersion() {
        return latestVersion;
    }

    public String getLanguage() {
        return language;
    }
}
