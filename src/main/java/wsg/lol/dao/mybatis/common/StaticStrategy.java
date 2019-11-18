package wsg.lol.dao.mybatis.common;

import java.util.List;

/**
 * Strategy interface for updating the static data.
 *
 * @author Kingen
 */
public interface StaticStrategy<T> {

    int clear();

    int batchInsert(List<T> list);
}
