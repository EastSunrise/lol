package wsg.lol.dao.mybatis.common.mapper;

import org.apache.ibatis.annotations.DeleteProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;
import wsg.lol.dao.mybatis.common.provider.ClearProvider;

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
