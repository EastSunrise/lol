package wsg.lol.dao.mybatis.mapper.lol.champion;

import org.springframework.stereotype.Repository;
import wsg.lol.common.pojo.domain.champion.ChampionTipDo;
import wsg.lol.dao.mybatis.common.mapper.StaticMapper;

/**
 * Mapper interface for tips of champions.
 *
 * @author Kingen
 */
@Repository
public interface ChampionTipMapper extends StaticMapper<ChampionTipDo> {
}