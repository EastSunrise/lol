package wsg.lol.dao.mybatis.mapper.lol.champion;

import org.springframework.stereotype.Repository;
import wsg.lol.common.annotation.Platform;
import wsg.lol.common.pojo.domain.champion.SkinDo;
import wsg.lol.dao.mybatis.common.StaticMapper;

/**
 * Mapper interface for skins of champions.
 *
 * @author Kingen
 */
@Platform
@Repository
public interface SkinMapper extends StaticMapper<SkinDo> {
}