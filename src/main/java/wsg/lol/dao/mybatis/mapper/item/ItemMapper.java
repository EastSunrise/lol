package wsg.lol.dao.mybatis.mapper.item;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.common.pojo.dto.item.ItemDto;
import wsg.lol.dao.mybatis.common.StaticStrategy;

/**
 * Mapper interface for base information of items.
 *
 * @author Kingen
 */
@Repository
@Mapper
public interface ItemMapper extends StaticStrategy<ItemDto> {
}