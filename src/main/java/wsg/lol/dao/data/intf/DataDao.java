package wsg.lol.dao.data.intf;

import wsg.lol.common.pojo.dto.state.ChampionDto;
import wsg.lol.common.pojo.dto.state.item.ItemExtDto;
import wsg.lol.common.pojo.dto.state.others.MapDto;
import wsg.lol.common.pojo.dto.state.rune.RuneTreeDto;
import wsg.lol.common.pojo.dto.state.spell.SummonerSpellDto;

import java.util.List;

/**
 * 静态数据接口
 *
 * @author EastSunrise
 */
public interface DataDao {

    List<ChampionDto> readChampions(String version);

    ItemExtDto readItems(String version);

    List<MapDto> readMaps(String version);

    List<RuneTreeDto> readRunes(String version);

    List<SummonerSpellDto> readSummonerSpells(String version);
}
