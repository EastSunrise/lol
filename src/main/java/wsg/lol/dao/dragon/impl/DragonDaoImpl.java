package wsg.lol.dao.dragon.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wsg.lol.common.base.AppException;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.common.enums.game.MapEnum;
import wsg.lol.common.pojo.deserializer.RecordExtraProcessor;
import wsg.lol.common.pojo.dto.champion.ChampionExtDto;
import wsg.lol.common.pojo.dto.champion.SpellDto;
import wsg.lol.common.pojo.dto.general.ImageDto;
import wsg.lol.common.pojo.dto.item.ItemExtDto;
import wsg.lol.common.pojo.dto.others.MapDto;
import wsg.lol.common.pojo.dto.rune.RuneDto;
import wsg.lol.common.pojo.dto.rune.RuneExtDto;
import wsg.lol.dao.dragon.config.StaticConfig;
import wsg.lol.dao.dragon.intf.DragonDao;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kingen
 */
@Component
public class DragonDaoImpl implements DragonDao {

    private static Logger logger = LoggerFactory.getLogger(DragonDao.class);

    private StaticConfig config;

    @Override
    public String getCdnDir(String version) {
        return StringUtils.joinWith(File.separator, config.getCdnDir(), "dragontail-" + version);
    }

    @Override
    public List<ChampionExtDto> readChampions(String version) {
        return new ArrayList<>(getDataMapByKey(version, DataKeyEnum.ChampionFull, ChampionExtDto.class).values());
    }

    @Override
    public List<ItemExtDto> readItems(String version) {
        Map<String, ItemExtDto> itemMap = getDataMapByKey(version, DataKeyEnum.Item, ItemExtDto.class);
        List<ItemExtDto> list = new ArrayList<>();
        for (Map.Entry<String, ItemExtDto> entry : itemMap.entrySet()) {
            ItemExtDto itemExtDto = entry.getValue();
            itemExtDto.setId(Integer.parseInt(entry.getKey()));
            list.add(itemExtDto);
        }
        return list;
    }

    @Override
    public List<MapDto> readMaps(String version) {
        Map<String, MapDto> map = getDataMapByKey(version, DataKeyEnum.Map, MapDto.class);
        List<MapDto> list = new ArrayList<>();
        for (Map.Entry<String, MapDto> entry : map.entrySet()) {
            MapDto mapDto = entry.getValue();
            mapDto.setMap(MapEnum.map(Integer.parseInt(entry.getKey())));
            list.add(mapDto);
        }
        return list;
    }

    @Override
    public List<RuneExtDto> readRunes(String version) {
        String jsonStr = getJsonStr(version, DataKeyEnum.Rune);
        JSONArray treeArray = JSON.parseArray(jsonStr);
        List<RuneExtDto> runeExtDtoList = new ArrayList<>();
        for (Object tree : treeArray) {
            RuneExtDto runeExtDto = JSON.parseObject(((JSON) tree).toJSONString(), RuneExtDto.class, new RecordExtraProcessor(DragonDao.class));
            JSONArray slotsJson = ((JSONObject) tree).getJSONArray("slots");
            RuneDto[][] slots = new RuneDto[slotsJson.size()][];
            for (int i = 0; i < slotsJson.size(); i++) {
                Object slot = slotsJson.get(i);
                JSONArray runesJson = ((JSONObject) slot).getJSONArray("runes");
                RuneDto[] runes = new RuneDto[runesJson.size()];
                for (int j = 0; j < runesJson.size(); j++) {
                    Object rune = runesJson.get(j);
                    runes[j] = JSON.parseObject(((JSON) rune).toJSONString(), RuneDto.class, new RecordExtraProcessor(DragonDao.class));
                }
                slots[i] = runes;
            }
            runeExtDto.setSlots(slots);
            runeExtDtoList.add(runeExtDto);
        }
        return runeExtDtoList;
    }

    @Override
    public List<ImageDto> readProfileIcons(String version) {
        FileDto<ProfileIconDto> fileDto = getFileDto(version, DataKeyEnum.ProfileIcon);
        List<ImageDto> imageDtoList = new ArrayList<>();
        for (ProfileIconDto profileIconDto : fileDto.data.values()) {
            ImageDto image = profileIconDto.getImage();
            image.setRelatedId(image.getId());
            image.setId(null);
            imageDtoList.add(image);
        }
        return imageDtoList;
    }

    @Override
    public List<SpellDto> readSummonerSpells(String version) {
        return new ArrayList<>(getDataMapByKey(version, DataKeyEnum.SummonerSpell, SpellDto.class).values());
    }

    private <T> FileDto<T> getFileDto(String version, DataKeyEnum key) {
        return JSON.parseObject(getJsonStr(version, key), new TypeReference<FileDto<T>>() {
        });
    }

    /**
     * Get the file of the key under the cdn of the defined version
     */
    private <T> Map<String, T> getDataMapByKey(String version, DataKeyEnum key, Class<T> clazz) {
        String jsonStr = getJsonStr(version, key);
        JSONObject data = JSON.parseObject(jsonStr).getJSONObject("data");
        Map<String, T> map = new HashMap<>();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            map.put(entry.getKey(), JSON.parseObject(((JSON) entry.getValue()).toJSONString(), clazz, new RecordExtraProcessor(DragonDao.class)));
        }
        return map;

    }

    @Autowired
    public void setConfig(StaticConfig config) {
        this.config = config;
    }

    /**
     * Get the json string from the file.
     */
    private String getJsonStr(String version, DataKeyEnum key) {
        String path = StringUtils.joinWith(File.separator, getCdnDir(version), version, "data", config.getLanguage(), key.getFileName());
        try {
            logger.info("Reading file " + path);
            return org.apache.commons.io.FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.error("Read file error.", e);
            throw new AppException(ErrorCodeConst.DRAGON_FILE_IO_ERROR);
        }
    }

    private enum DataKeyEnum {
        ChampionFull("championFull.json"),
        Item("item.json"),
        Map("map.json"),
        Rune("runesReforged.json"),
        ProfileIcon("profileicon.json"),
        SummonerSpell("summoner.json"),
        ;
        private String fileName;

        DataKeyEnum(String fileName) {
            this.fileName = fileName;
        }

        public String getFileName() {
            return fileName;
        }
    }

    @Data
    private static class FileDto<T> {
        private Map<String, T> data;
    }

    @Data
    private static class ProfileIconDto {
        private Integer id;
        private ImageDto image;
    }

}
