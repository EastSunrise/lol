package wsg.lol.dao.dragon.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.Data;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wsg.lol.common.base.AppException;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.common.pojo.deserializer.CustomParseConfig;
import wsg.lol.common.pojo.deserializer.RecordExtraProcessor;
import wsg.lol.common.pojo.dto.champion.ChampionExtDto;
import wsg.lol.common.pojo.dto.champion.SpellDto;
import wsg.lol.common.pojo.dto.general.ImageDto;
import wsg.lol.common.pojo.dto.item.ItemExtDto;
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
        return getValues(version, JsonTypeEnum.ChampionFull, new TypeReference<FileDto<ChampionExtDto>>() {});
    }

    @Override
    public List<ItemExtDto> readItems(String version) {
        Map<String, ItemExtDto> map = getMap(version, JsonTypeEnum.Item, new TypeReference<FileDto<ItemExtDto>>() {});
        List<ItemExtDto> list = new ArrayList<>();
        for (Map.Entry<String, ItemExtDto> entry : map.entrySet()) {
            ItemExtDto itemExtDto = entry.getValue();
            itemExtDto.setId(Integer.parseInt(entry.getKey()));
            list.add(itemExtDto);
        }
        return list;
    }

    @Override
    public Map<Integer, ImageDto> readMaps(String version) {
        Map<String, ImageDto> map = getImageMap(version, JsonTypeEnum.Map);
        Map<Integer, ImageDto> result = new HashMap<>();
        for (Map.Entry<String, ImageDto> entry : map.entrySet()) {
            result.put(Integer.parseInt(entry.getKey()), entry.getValue());
        }
        return result;
    }

    @Override
    public List<RuneExtDto> readRunes(String version) {
        return JSON.parseArray(getJsonStr(version, JsonTypeEnum.Rune), RuneExtDto.class);
    }

    @Override
    public Map<Integer, ImageDto> readProfileIcons(String version) {
        Map<String, ImageDto> map = getImageMap(version, JsonTypeEnum.ProfileIcon);
        Map<Integer, ImageDto> result = new HashMap<>();
        for (Map.Entry<String, ImageDto> entry : map.entrySet()) {
            result.put(Integer.parseInt(entry.getKey()), entry.getValue());
        }
        return result;
    }

    @Override
    public List<SpellDto> readSummonerSpells(String version) {
        return getValues(version, JsonTypeEnum.SummonerSpell, new TypeReference<FileDto<SpellDto>>() {});
    }

    @Override
    public Map<String, String> readLanguages(String version) {
        return getMap(version, JsonTypeEnum.Language, new TypeReference<FileDto<String>>() {});
    }

    @Override
    public Map<String, ImageDto> readMissionAssets(String version) {
        return getImageMap(version, JsonTypeEnum.MissionAsset);
    }

    private Map<String, ImageDto> getImageMap(String version, JsonTypeEnum key) {
        Map<String, ImageExtDto> map = getMap(version, key, new TypeReference<FileDto<ImageExtDto>>() {});
        Map<String, ImageDto> result = new HashMap<>();
        for (Map.Entry<String, ImageExtDto> entry : map.entrySet()) {
            result.put(entry.getKey(), entry.getValue().getImage());
        }
        return result;
    }

    private <T> List<T> getValues(String version, JsonTypeEnum key, TypeReference<FileDto<T>> typeReference) {
        return new ArrayList<>(getMap(version, key, typeReference).values());
    }

    @SuppressWarnings("unchecked")
    private <T> Map<String, T> getMap(String version, JsonTypeEnum key, TypeReference<FileDto<T>> typeReference) {
        return ((FileDto<T>) JSON.parseObject(getJsonStr(version, key), typeReference.getType(), new CustomParseConfig(), new RecordExtraProcessor(DragonDao.class), JSON.DEFAULT_PARSER_FEATURE)).getData();
    }

    @Autowired
    public void setConfig(StaticConfig config) {
        this.config = config;
    }

    /**
     * Get the json string from the file.
     */
    private String getJsonStr(String version, JsonTypeEnum key) {
        String path = StringUtils.joinWith(File.separator, getCdnDir(version), version, "data", config.getLanguage(), key.getFilename());
        try {
            logger.info("Reading file " + path);
            return FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.error("Read file error.", e);
            throw new AppException(ErrorCodeConst.DRAGON_FILE_IO_ERROR);
        }
    }

    private enum JsonTypeEnum {
        ChampionFull("championFull.json"),
        Item("item.json"),
        Map("map.json"),
        Rune("runesReforged.json"),
        ProfileIcon("profileicon.json"),
        SummonerSpell("summoner.json"),
        Language("language.json"),
        MissionAsset("mission-assets"),
        ;

        private String filename;

        JsonTypeEnum(String filename) {
            this.filename = filename;
        }

        public String getFilename() {
            return filename;
        }
    }

    /**
     * Bean for the whole json file with "data".
     */
    @Data
    private static class FileDto<T> {
        private Map<String, T> data;
    }

    /**
     * Bean for extended images.
     */
    @Data
    private static class ImageExtDto {
        private ImageDto image;
    }
}
