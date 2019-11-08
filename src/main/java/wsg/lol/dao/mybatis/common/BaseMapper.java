package wsg.lol.dao.mybatis.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import wsg.lol.common.pojo.base.Persistable;

/**
 * wsg
 *
 * @author EastSunrise
 */
public interface BaseMapper<T extends Persistable> extends Mapper<T>, MySqlMapper<T> {
}
