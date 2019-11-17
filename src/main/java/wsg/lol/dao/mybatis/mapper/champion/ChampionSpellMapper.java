package wsg.lol.dao.mybatis.mapper.champion;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.common.pojo.dto.champion.ChampionSpellDto;
import wsg.lol.dao.mybatis.common.StateStrategy;

@Repository
@Mapper
public interface ChampionSpellMapper extends StateStrategy<ChampionSpellDto> {

    ChampionSpellDto selectByPrimaryKey(String id);
}