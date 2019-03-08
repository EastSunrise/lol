package wsg.lol.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import wsg.lol.dmo.champion.ChampionDmo;
import wsg.lol.dmo.champion.SkinDmo;
import wsg.lol.dmo.champion.SpellDmo;
import wsg.lol.dmo.champion.TipDmo;

import java.util.List;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-08 09:37
 */
@Mapper
public interface ChampionMapper {
    int batchInsertChampions(List<ChampionDmo> championDmoList);

    int batchInsertSkins(List<SkinDmo> skinDmoList);

    int batchInsertSpells(List<SpellDmo> spellDmoList);

    int batchInsertTips(List<TipDmo> tipDmoList);
}
