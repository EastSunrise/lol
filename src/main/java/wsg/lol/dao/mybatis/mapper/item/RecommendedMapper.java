package wsg.lol.dao.mybatis.mapper.item;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.common.pojo.dto.item.RecommendedDto;
import wsg.lol.dao.mybatis.common.StaticStrategy;

/**
 * Mapper interface for recommended items.
 *
 * @author Kingen
 */
@Repository
@Mapper
public interface RecommendedMapper extends StaticStrategy<RecommendedDto> {
}