package wsg.lol.config;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import wsg.lol.common.enums.system.PlatformRoutingEnum;

/**
 * Global configuration.
 *
 * @author Kingen
 */
@Configuration
@ConfigurationProperties(prefix = "global")
public class GlobalConfig {

    @Setter
    private PlatformRoutingEnum region;

    public PlatformRoutingEnum getRegion() {
        return region;
    }
}
