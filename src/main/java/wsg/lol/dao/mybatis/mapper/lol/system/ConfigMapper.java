package wsg.lol.dao.mybatis.mapper.lol.system;

import org.springframework.stereotype.Repository;
import wsg.lol.common.enums.system.RegionEnum;

/**
 * Mapper interface for configurations.
 *
 * @author Kingen
 */
@Repository
public interface ConfigMapper {

    String getConfigValue(String name, RegionEnum region);

    int updateConfigValue(RegionEnum region, String name, String value);
}