package wsg.lol.dao.data.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wsg.lol.common.pojo.base.BaseDto;
import wsg.lol.common.pojo.base.IJson;
import wsg.lol.common.pojo.dto.state.ChampionDto;
import wsg.lol.common.pojo.dto.state.ItemDto;
import wsg.lol.common.pojo.dto.state.item.GroupDto;
import wsg.lol.common.pojo.dto.state.item.ItemExtDto;
import wsg.lol.common.pojo.dto.state.item.TreeDto;
import wsg.lol.common.pojo.dto.state.others.MapDto;
import wsg.lol.common.pojo.dto.state.rune.RuneTreeDto;
import wsg.lol.common.pojo.dto.state.spell.SummonerSpellDto;
import wsg.lol.common.util.FileUtils;
import wsg.lol.dao.data.config.StateConfig;
import wsg.lol.dao.data.intf.DataDao;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * wsg
 *
 * @author EastSunrise
 */
@Component
public class DataDaoImpl implements DataDao {

    private static Logger logger = LoggerFactory.getLogger(DataDao.class);

    private StateConfig config;

    @Override
    public List<ChampionDto> readChampions(String version) {
        return getDataList(DataKeyEnum.championFull, ChampionDto.class, version);
    }

    @Override
    public ItemExtDto readItems(String version) {
        ItemExtDto itemExtDto = new ItemExtDto();
        JSONObject data = getDataByKey(DataKeyEnum.item, version);

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
        return getDataList(DataKeyEnum.map, MapDto.class, version);
    }

    @Override
    public List<RuneTreeDto> readRunes(String version) {
        String str = FileUtils.readString(StringUtils.join(getDir(version), DataKeyEnum.runesReforged + ".json"));
        return JSON.parseArray(str, RuneTreeDto.class);
    }

    @Override
    public List<SummonerSpellDto> readSummonerSpells(String version) {
        return getDataList(DataKeyEnum.summoner, SummonerSpellDto.class, version);
    }

    private <T extends BaseDto & IJson> List<T> getDataList(DataKeyEnum dataKeyEnum, Class<T> clazz, String version) {
        JSONObject data = getDataByKey(dataKeyEnum, version).getJSONObject("data");
        List<T> dataList = new LinkedList<>();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            logger.info("Parsing json to java: " + dataKeyEnum + ", " + entry.getKey());
            dataList.add(JSON.toJavaObject((JSON) entry.getValue(), clazz));
        }
        return dataList;
    }

    private JSONObject getDataByKey(DataKeyEnum key, String version) {
        return FileUtils.readJSONObject(StringUtils.join(getDir(version), key + ".json"));
    }

    private String getDir(String version) {
        return StringUtils.join(config.getDataDir(), version, "data", config.getLanguage());
    }

    @Autowired
    public void setConfig(StateConfig config) {
        this.config = config;
    }

    private enum DataKeyEnum {
        championFull, summoner, item, map, runesReforged
    }
}
