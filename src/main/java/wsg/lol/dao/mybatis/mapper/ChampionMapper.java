package wsg.lol.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import wsg.lol.pojo.dmo.champion.ChampionDmo;
import wsg.lol.pojo.dmo.champion.SkinDmo;
import wsg.lol.pojo.dmo.champion.SpellDmo;
import wsg.lol.pojo.dto.query.GetChampionDto;
import wsg.lol.pojo.dto.query.GetChampionListDto;

import java.util.List;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-08 09:37
 */
@Mapper
public interface ChampionMapper {

    List<ChampionDmo> selectChampions(GetChampionListDto getChampionListDto);

    int batchInsertOrUpdateChampions(List<ChampionDmo> championDmoList);

    int batchInsertSkins(List<SkinDmo> skinDmoList);

    int batchInsertSpells(List<SpellDmo> spellDmoList);

    ChampionDmo selectChampionByCond(GetChampionDto getChampionDto);
}
