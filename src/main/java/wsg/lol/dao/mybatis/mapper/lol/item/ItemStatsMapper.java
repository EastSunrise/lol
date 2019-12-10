package wsg.lol.dao.mybatis.mapper.lol.item;

import org.springframework.stereotype.Repository;
import wsg.lol.common.annotation.Platform;
import wsg.lol.common.pojo.domain.share.ItemStatsDo;
import wsg.lol.dao.mybatis.common.StaticMapper;

/**
 * Mapper interface for stats of items.
 *
 * @author Kingen
 */
@Platform
@Repository
public interface ItemStatsMapper extends StaticMapper<ItemStatsDo> {
}