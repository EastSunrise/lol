package wsg.lol.dao.mybatis.mapper.summoner;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import wsg.lol.common.pojo.dto.summoner.ChampionMasteryDto;

/**
 * Mapper for champion masteries.
 *
 * @author Kingen
 */
@Mapper
@Repository
public interface ChampionMasteryMapper extends InsertListMapper<ChampionMasteryDto> {
}