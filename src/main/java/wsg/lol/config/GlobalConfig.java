package wsg.lol.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import wsg.lol.common.enums.system.RegionEnum;

/**
 * Global configuration.
 *
 * @author Kingen
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "global")
public class GlobalConfig {

    private RegionEnum region;

    private int timeout;
}
