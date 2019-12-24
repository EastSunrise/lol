package wsg.lol.dao.mybatis.mapper.lol.system;

import org.springframework.stereotype.Repository;

/**
 * Mapper interface for configurations.
 *
 * @author Kingen
 */
@Repository
public interface ConfigMapper {

    String getConfigValue(String name);

    int updateConfigValue(String name, String value);
}