package wsg.lol.config;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import wsg.lol.common.enums.system.RegionEnum;

/**
 * Global configuration.
 *
 * @author Kingen
 */
@Configuration
@ConfigurationProperties(prefix = "global")
public class GlobalConfig {

    @Setter
    private RegionEnum region;

    public RegionEnum getRegion() {
        return region;
    }
}
