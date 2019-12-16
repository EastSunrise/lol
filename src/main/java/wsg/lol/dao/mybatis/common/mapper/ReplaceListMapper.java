package wsg.lol.dao.mybatis.common.mapper;

import org.apache.ibatis.annotations.InsertProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;
import wsg.lol.dao.mybatis.common.provider.ReplaceListProvider;

import java.util.List;

/**
 * Custom mapper interface for list of data.
 *
 * @author Kingen
 */
@RegisterMapper
public interface ReplaceListMapper<T> {

    /**
     * Replace each record. That's to say, update the record if exists, otherwise insert it.
     */
    @InsertProvider(type = ReplaceListProvider.class, method = "dynamicSQL")
    int replaceList(List<? extends T> recordList);
}
