package wsg.lol.dao.mybatis.mapper.champion;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.common.pojo.dto.champion.ChampionStatsDto;
import wsg.lol.dao.mybatis.common.StaticMapper;

/**
 * Mapper interface for stats of champions.
 *
 * @author Kingen
 */
@Repository
@Mapper
public interface ChampionStatsMapper extends StaticMapper<ChampionStatsDto> {
}