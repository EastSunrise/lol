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

    List<ChampionDto> readChampions();

    ItemExtDto readItems();

    List<MapDto> readMaps();

    List<RuneTreeDto> readRunes();

    List<SummonerSpellDto> readSummonerSpells();
}
