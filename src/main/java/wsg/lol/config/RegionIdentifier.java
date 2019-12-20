package wsg.lol.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private static RegionEnum DEFAULT_REGION;

    public static RegionEnum getRegion() {
        RegionEnum region = threadLocal.get();
        return region == null ? DEFAULT_REGION : region;
    }

    public static void setPlatform(RegionEnum region) {
        threadLocal.set(region == null ? DEFAULT_REGION : region);
    }

    @Autowired
    public void setDefaultRegion(@Value("${api.region}") RegionEnum region) {
        DEFAULT_REGION = region;
    }
}
