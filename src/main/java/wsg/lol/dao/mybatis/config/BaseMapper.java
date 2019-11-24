package wsg.lol.dao.mybatis.config;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * todo
 *
 * @author EastSunrise
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
