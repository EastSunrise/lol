package wsg.lol.dao.mybatis.mapper.system;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ConfigMapper {

    String getConfigValue(String name);

    int updateConfigValue(String name, String value);
}