package wsg.lol.dao.mybatis.mapper.region.summoner;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import wsg.lol.common.pojo.domain.summoner.ChampionMasteryDo;
import wsg.lol.dao.mybatis.common.mapper.ReplaceListMapper;

/**
 * Mapper for champion masteries.
 *
 * @author Kingen
 */
@Repository
public interface ChampionMasteryMapper extends InsertListMapper<ChampionMasteryDo>, ReplaceListMapper<ChampionMasteryDo> {
}