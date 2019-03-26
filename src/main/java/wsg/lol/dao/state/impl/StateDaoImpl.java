package wsg.lol.dao.state.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wsg.lol.common.utils.FileUtil;
import wsg.lol.common.utils.LogUtil;
import wsg.lol.dao.config.FileConfig;
import wsg.lol.dao.state.intf.StateDao;
import wsg.lol.pojo.base.StateBean;
import wsg.lol.pojo.dto.state.champion.ChampionDto;
import wsg.lol.pojo.dto.state.item.GroupDto;
import wsg.lol.pojo.dto.state.item.ItemDto;
import wsg.lol.pojo.dto.state.item.ItemExtDto;
import wsg.lol.pojo.dto.state.item.TreeDto;
import wsg.lol.pojo.dto.state.others.MapDto;
import wsg.lol.pojo.dto.state.rune.RuneTreeDto;
import wsg.lol.pojo.dto.state.spell.SummonerSpellDto;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * wsg
 *
 * @author wangsigen
 */
@Component
public class StateDaoImpl implements StateDao {

    private FileConfig config;

    @Override
    public List<ChampionDto> readChampions() {
        return getDataList(KeyEnum.championFull, ChampionDto.class);
    }

    @Override
    public ItemExtDto readItems() {
        ItemExtDto itemExtDto = new ItemExtDto();
        JSONObject data = getDataByKey(KeyEnum.item);

        JSONObject item = data.getJSONObject("data");
        List<ItemDto> itemDtoList = new LinkedList<>();
        for (Map.Entry<String, Object> entry : item.entrySet()) {
            ItemDto itemDto = JSON.toJavaObject((JSON) entry.getValue(), ItemDto.class);
            itemDto.setId(entry.getKey());
            itemDtoList.add(itemDto);
        }
        itemExtDto.setItemDtoList(itemDtoList);

        String groups = data.getString("groups");
        itemExtDto.setGroupDtoList(JSON.parseArray(groups, GroupDto.class));

        String tree = data.getString("tree");
        itemExtDto.setTreeDtoList(JSON.parseArray(tree, TreeDto.class));

        return itemExtDto;
    }

    @Override
    public List<MapDto> readMaps() {
        return getDataList(KeyEnum.map, MapDto.class);
    }

    @Override
    public List<RuneTreeDto> readRunes() {
        String str = FileUtil.readString(FileUtil.concat2Path(getDir(), KeyEnum.runesReforged + ".json"));
        return JSON.parseArray(str, RuneTreeDto.class);
    }

    @Override
    public List<SummonerSpellDto> readSummonerSpells() {
        return getDataList(KeyEnum.summoner, SummonerSpellDto.class);
    }

    private <T extends StateBean> List<T> getDataList(KeyEnum keyEnum, Class<T> clazz) {
        JSONObject data = getDataByKey(keyEnum).getJSONObject("data");
        List<T> dataList = new LinkedList<>();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            LogUtil.info("Parsing json to java: " + keyEnum + ", " + entry.getKey());
            dataList.add(JSON.toJavaObject((JSON) entry.getValue(), clazz));
        }
        return dataList;
    }

    private JSONObject getDataByKey(KeyEnum key) {
        return FileUtil.readJSONObject(FileUtil.concat2Path(getDir(), key + ".json"));
    }

    private String getDir() {
        return FileUtil.concat2Path(config.getDataDir(), config.getLatestVersion(), "data", config.getLanguage());
    }

    @Autowired
    public void setConfig(FileConfig config) {
        this.config = config;
    }

    private enum KeyEnum {
        championFull, summoner, item, map, runesReforged
    }
}
