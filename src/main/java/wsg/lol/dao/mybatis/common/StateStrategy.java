package wsg.lol.dao.mybatis.common;

import java.util.List;

public interface StateStrategy<T> {

    int clear();

    int batchInsert(List<T> list);
}
