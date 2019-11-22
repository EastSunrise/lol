package wsg.lol.dao.mybatis.mapper.summoner;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.common.pojo.dto.summoner.ChampionMasteryDto;
import wsg.lol.dao.mybatis.common.StaticStrategy;

/**
 * Mapper for champion masteries.
 *
 * @author Kingen
 */
@Mapper
@Repository
public interface ChampionMasteryMapper extends StaticStrategy<ChampionMasteryDto> {
}