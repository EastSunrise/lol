package wsg.lol.dao.state.intf;

import wsg.lol.pojo.dto.state.champion.ChampionDto;
import wsg.lol.pojo.dto.state.item.ItemExtDto;
import wsg.lol.pojo.dto.state.others.MapDto;
import wsg.lol.pojo.dto.state.rune.RuneTreeDto;
import wsg.lol.pojo.dto.state.spell.SummonerSpellDto;

import java.util.List;

/**
 * wsg
 *
 * @author wangsigen
 */
public interface StateDao {

    String readLatestVersion();

    List<ChampionDto> readChampions(String version);

    ItemExtDto readItems(String version);

    List<MapDto> readMaps(String version);

    List<RuneTreeDto> readRunes(String version);

    List<SummonerSpellDto> readSummonerSpells(String version);
}
