package wsg.lol.dao.mybatis.common;

import org.apache.ibatis.annotations.DeleteProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

/**
 * Strategy interface for updating the static data.
 *
 * @author Kingen
 */
@RegisterMapper
public interface ClearMapper<T> {
    @DeleteProvider(type = ClearProvider.class, method = "dynamicSQL")
    int clear();
}
