package wsg.lol.dao.dragon.intf;

import wsg.lol.common.pojo.dto.champion.ChampionExtDto;
import wsg.lol.common.pojo.dto.champion.SpellDto;
import wsg.lol.common.pojo.dto.item.ItemExtDto;
import wsg.lol.common.pojo.dto.rune.RuneExtDto;
import wsg.lol.common.pojo.dto.share.ImageDto;

import java.util.List;
import java.util.Map;

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
     * Read the data of images of maps.
     *
     * @return key-values for map id mapping the image of the map.
     */
    Map<Integer, ImageDto> readMaps(String version);

    /**
     * Read reforged runes.
     */
    List<RuneExtDto> readRunes(String version);

    /**
     * Read the data of images of profile icons.
     *
     * @return key-values for profile icon id mapping the image of the icon.
     */
    Map<Integer, ImageDto> readProfileIcons(String version);

    /**
     * Read the data of summoner spells.
     */
    List<SpellDto> readSummonerSpells(String version);

    /**
     * Read the data of languages.
     */
    Map<String, String> readLanguages(String version);

    /**
     * Read the data of mission assets.
     */
    Map<String, ImageDto> readMissionAssets(String version);
}
