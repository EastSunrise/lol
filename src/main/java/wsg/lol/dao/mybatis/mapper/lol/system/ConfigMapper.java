package wsg.lol.dao.mybatis.mapper.lol.system;

import org.springframework.stereotype.Repository;
import wsg.lol.common.annotation.Platform;
import wsg.lol.common.enums.system.PlatformRoutingEnum;

/**
 * Mapper interface for configurations.
 *
 * @author Kingen
 */
@Platform
@Repository
public interface ConfigMapper {

    String getConfigValue(String name, PlatformRoutingEnum region);

    int updateConfigValue(PlatformRoutingEnum region, String name, String value);
}