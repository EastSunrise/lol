package wsg.lol.dao.mybatis.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * wsg
 *
 * @author EastSunrise
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
