package wsg.lol.dao.mybatis.common;

import tk.mybatis.mapper.additional.insert.InsertListMapper;

/**
 * Mapper for data in the dragon.
 *
 * @author Kingen
 */
public interface StaticMapper<T> extends ClearMapper<T>, InsertListMapper<T> {
}
