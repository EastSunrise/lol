package wsg.lol.dao.mybatis.mapper.region.summoner;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeySelectiveMapper;
import wsg.lol.common.pojo.domain.summoner.ChampionMasteryDo;

/**
 * Mapper for champion masteries.
 *
 * @author Kingen
 */
@Repository
public interface ChampionMasteryMapper extends InsertListMapper<ChampionMasteryDo>, UpdateByPrimaryKeySelectiveMapper<ChampionMasteryDo> {
}