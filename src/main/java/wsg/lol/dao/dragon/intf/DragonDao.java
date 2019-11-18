package wsg.lol.dao.dragon.intf;

import wsg.lol.common.pojo.dto.champion.ChampionExtDto;
import wsg.lol.common.pojo.dto.item.ItemExtDto;
import wsg.lol.common.pojo.dto.others.MapDto;
import wsg.lol.common.pojo.dto.rune.RuneExtDto;

import java.util.List;

/**
 * Static data interface.
 *
 * @author Kingen
 */
public interface DragonDao {

    /**
     * Get the cdn directory.
     */
    String getCdnDir(String version);

    /**
     * Read the data of champions.
     */
    List<ChampionExtDto> readChampions(String version);

    /**
     * Read the data of items.
     */
    List<ItemExtDto> readItems(String version);

    /**
     * Read the data of maps.
     */
    List<MapDto> readMaps(String version);

    /**
     * Read the data of reforged runes
     */
    List<RuneExtDto> readRunes(String version);
}
