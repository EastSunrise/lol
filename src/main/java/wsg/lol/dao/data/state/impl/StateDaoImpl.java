package wsg.lol.dao.data.state.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wsg.lol.common.utils.FileUtil;
import wsg.lol.common.utils.HttpHelper;
import wsg.lol.dao.data.config.StateConfig;
import wsg.lol.dao.data.state.intf.StateDao;
import wsg.lol.pojo.base.BaseDto;
import wsg.lol.pojo.base.IJson;
import wsg.lol.pojo.dto.state.ChampionDto;
import wsg.lol.pojo.dto.state.ItemDto;
import wsg.lol.pojo.dto.state.item.GroupDto;
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

    private static Logger logger = LoggerFactory.getLogger(StateDao.class);

    private StateConfig config;

    @Override
    public String readLatestVersion() {
        List<String> versions = JSON.parseArray(HttpHelper.getString(config.getVersionPath()), String.class);
        return versions.get(0);
    }

    @Override
    public List<ChampionDto> readChampions(String version) {
        return getDataList(KeyEnum.championFull, ChampionDto.class, version);
    }

    @Override
    public ItemExtDto readItems(String version) {
        ItemExtDto itemExtDto = new ItemExtDto();
        JSONObject data = getDataByKey(KeyEnum.item, version);

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
    public List<MapDto> readMaps(String version) {
        return getDataList(KeyEnum.map, MapDto.class, version);
    }

    @Override
    public List<RuneTreeDto> readRunes(String version) {
        String str = FileUtil.readString(FileUtil.concat2Path(getDir(version), KeyEnum.runesReforged + ".json"));
        return JSON.parseArray(str, RuneTreeDto.class);
    }

    @Override
    public List<SummonerSpellDto> readSummonerSpells(String version) {
        return getDataList(KeyEnum.summoner, SummonerSpellDto.class, version);
    }

    private <T extends BaseDto & IJson> List<T> getDataList(KeyEnum keyEnum, Class<T> clazz, String version) {
        JSONObject data = getDataByKey(keyEnum, version).getJSONObject("data");
        List<T> dataList = new LinkedList<>();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            logger.info("Parsing json to java: " + keyEnum + ", " + entry.getKey());
            dataList.add(JSON.toJavaObject((JSON) entry.getValue(), clazz));
        }
        return dataList;
    }

    private JSONObject getDataByKey(KeyEnum key, String version) {
        return FileUtil.readJSONObject(FileUtil.concat2Path(getDir(version), key + ".json"));
    }

    private String getDir(String version) {
        return FileUtil.concat2Path(config.getDataDir(), version, "data", config.getLanguage());
    }

    @Autowired
    public void setConfig(StateConfig config) {
        this.config = config;
    }

    private enum KeyEnum {
        championFull, summoner, item, map, runesReforged
    }
}
