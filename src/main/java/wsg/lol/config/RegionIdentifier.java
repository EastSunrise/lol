package wsg.lol.config;

import org.springframework.context.annotation.Configuration;
import wsg.lol.common.enums.system.RegionEnum;

/**
 * Identifier for different databases.
 *
 * @author Kingen
 */
@Configuration
public class RegionIdentifier {

    private static final ThreadLocal<RegionEnum> threadLocal = new ThreadLocal<>();

    public static RegionEnum getRegion() {
        return threadLocal.get();
    }

    public static void setPlatform(RegionEnum region) {
        threadLocal.set(region);
    }
}
