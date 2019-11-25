package wsg.lol.dao.mybatis.config;

import tk.mybatis.mapper.additional.insert.InsertListMapper;

/**
 * Mapper for data in the dragon.
 *
 * @author Kingen
 */
public interface StaticMapper<T> extends ClearMapper<T>, InsertListMapper<T> {
}
