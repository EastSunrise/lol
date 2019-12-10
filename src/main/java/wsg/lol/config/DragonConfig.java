package wsg.lol.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configurable arguments in config file.
 *
 * @author Kingen
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "dragon")
public class DragonConfig {

    private String directory;

    private String language;

    private Url url;

    @Data
    public static class Url {
        private String cdn;

        private String realm;

        private String version;
    }


}
