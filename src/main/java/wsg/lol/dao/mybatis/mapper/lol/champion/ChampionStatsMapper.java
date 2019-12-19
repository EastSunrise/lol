package wsg.lol.dao.mybatis.mapper.lol.champion;

import org.springframework.stereotype.Repository;
import wsg.lol.common.pojo.domain.champion.ChampionStatsDo;
import wsg.lol.dao.mybatis.common.mapper.StaticMapper;

/**
 * Mapper interface for stats of champions.
 *
 * @author Kingen
 */
@Repository
public interface ChampionStatsMapper extends StaticMapper<ChampionStatsDo> {
}