package wsg.lol.dao.mybatis.mapper.system;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.common.enums.system.PlatformRoutingEnum;

/**
 * Mapper interface for configurations.
 *
 * @author Kingen
 */
@Repository
@Mapper
public interface ConfigMapper {

    String getConfigValue(String name, PlatformRoutingEnum region);

    int updateConfigValue(PlatformRoutingEnum region, String name, String value);
}