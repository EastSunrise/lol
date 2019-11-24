package wsg.lol.dao.mybatis.mapper.item;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.common.pojo.dto.item.BlockDto;
import wsg.lol.dao.mybatis.config.StaticStrategy;

/**
 * Mapper interface for blocks of recommended items.
 *
 * @author Kingen
 */
@Repository
@Mapper
public interface BlockMapper extends StaticStrategy<BlockDto> {
}