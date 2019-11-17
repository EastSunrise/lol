package wsg.lol.dao.mybatis.mapper.champion;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.common.pojo.dto.champion.ChampionStatsDto;
import wsg.lol.dao.mybatis.common.StateStrategy;

@Repository
@Mapper
public interface ChampionStatsMapper extends StateStrategy<ChampionStatsDto> {

    ChampionStatsDto selectByPrimaryKey(String id);
}