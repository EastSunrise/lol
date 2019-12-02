package wsg.lol.dao.mybatis.mapper.lol.item;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.common.annotation.Platform;
import wsg.lol.common.pojo.domain.share.ItemDo;
import wsg.lol.dao.mybatis.common.StaticMapper;

/**
 * Mapper interface for base information of items.
 *
 * @author Kingen
 */
@Repository
@Mapper
@Platform
public interface ItemMapper extends StaticMapper<ItemDo> {
}