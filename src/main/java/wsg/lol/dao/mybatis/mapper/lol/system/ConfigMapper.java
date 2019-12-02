package wsg.lol.dao.mybatis.mapper.lol.system;

import org.apache.ibatis.annotations.Mapper;
import wsg.lol.common.annotation.Platform;
import wsg.lol.common.enums.system.PlatformRoutingEnum;

/**
 * Mapper interface for configurations.
 *
 * @author Kingen
 */
@Mapper
@Platform
public interface ConfigMapper {

    String getConfigValue(String name, PlatformRoutingEnum region);

    int updateConfigValue(PlatformRoutingEnum region, String name, String value);
}