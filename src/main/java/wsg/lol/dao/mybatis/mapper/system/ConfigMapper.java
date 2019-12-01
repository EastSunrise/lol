package wsg.lol.dao.mybatis.mapper.system;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.common.annotation.Datasource;
import wsg.lol.common.enums.system.PlatformRoutingEnum;

/**
 * Mapper interface for configurations.
 *
 * @author Kingen
 */
@Repository
@Mapper
public interface ConfigMapper {

    @Datasource
    String getConfigValue(String name, PlatformRoutingEnum region);

    @Datasource
    int updateConfigValue(PlatformRoutingEnum region, String name, String value);
}