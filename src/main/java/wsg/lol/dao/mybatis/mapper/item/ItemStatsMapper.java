package wsg.lol.dao.mybatis.mapper.item;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.common.pojo.dto.item.ItemStatsDto;
import wsg.lol.dao.mybatis.common.StaticStrategy;

/**
 * Mapper interface for stats of items.
 *
 * @author Kingen
 */
@Repository
@Mapper
public interface ItemStatsMapper extends StaticStrategy<ItemStatsDto> {
}