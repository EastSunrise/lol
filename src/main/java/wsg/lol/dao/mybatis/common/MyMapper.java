package wsg.lol.dao.mybatis.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import wsg.lol.pojo.base.Persistable;

/**
 * wsg
 *
 * @author wangsigen
 */
public interface MyMapper<T extends Persistable> extends Mapper<T>, MySqlMapper<T> {
}
