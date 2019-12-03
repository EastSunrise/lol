package wsg.lol.dao.mybatis.mapper.lol.item;

import org.springframework.stereotype.Repository;
import wsg.lol.common.annotation.Platform;
import wsg.lol.common.pojo.domain.share.RecommendedDo;
import wsg.lol.dao.mybatis.common.StaticMapper;

/**
 * Mapper interface for recommended items.
 *
 * @author Kingen
 */
@Platform
@Repository
public interface RecommendedMapper extends StaticMapper<RecommendedDo> {
}