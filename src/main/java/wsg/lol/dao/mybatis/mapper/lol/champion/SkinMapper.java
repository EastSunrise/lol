package wsg.lol.dao.mybatis.mapper.lol.champion;

import org.springframework.stereotype.Repository;
import wsg.lol.common.pojo.domain.champion.SkinDo;
import wsg.lol.dao.mybatis.common.mapper.StaticMapper;

/**
 * Mapper interface for skins of champions.
 *
 * @author Kingen
 */
@Repository
public interface SkinMapper extends StaticMapper<SkinDo> {
}