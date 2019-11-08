package wsg.lol.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * // TODO: (wangsigen, 2019/11/8)
 *
 * @author wangsigen
 * @date 2019/11/8
 * @since 2.4.9.3
 */
@Repository
@Mapper
public interface ConfigMapper {

    String getConfigValue(String name);

    /**
     * update the config value specified by name.
     */
    int updateConfig(String configNameCurrentVersion, String configValue);
}
