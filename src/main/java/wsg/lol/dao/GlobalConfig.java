package wsg.lol.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import wsg.lol.common.enums.system.PlatformRoutingEnum;

/**
 * Global configuration.
 *
 * @author Kingen
 */
@Configuration
public class GlobalConfig {

    @Value("${global.region}")
    private PlatformRoutingEnum region;

    public PlatformRoutingEnum getRegion() {
        return region;
    }
}
