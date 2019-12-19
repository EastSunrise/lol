package wsg.lol.dao.mybatis.mapper.lol.item;

import org.springframework.stereotype.Repository;
import wsg.lol.common.pojo.domain.item.ItemDo;
import wsg.lol.dao.mybatis.common.mapper.StaticMapper;

/**
 * Mapper interface for base information of items.
 *
 * @author Kingen
 */
@Repository
public interface ItemMapper extends StaticMapper<ItemDo> {
}