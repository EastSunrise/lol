package wsg.lol.dao.data.intf;

import wsg.lol.common.pojo.dto.champion.ChampionExtDto;
import wsg.lol.common.pojo.dto.item.ItemExtDto;
import wsg.lol.common.pojo.dto.others.MapDto;
import wsg.lol.common.pojo.dto.rune.RuneExtDto;

import java.util.List;

/**
 * 静态数据接口
 *
 * @author EastSunrise
 */
public interface DataDao {

    /**
     * 获取版本数据目录
     */
    String getCdnDir(String version);

    List<ChampionExtDto> readChampions(String version);

    List<ItemExtDto> readItems(String version);

    List<MapDto> readMaps(String version);

    List<RuneExtDto> readRunes(String version);
}
