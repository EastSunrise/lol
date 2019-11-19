package wsg.lol.dao.dragon.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wsg.lol.common.base.AppException;
import wsg.lol.common.constant.ErrorCodeConst;
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
        return getValues(version, DataKeyEnum.ChampionFull, new TypeReference<FileDto<ChampionExtDto>>() {});
    }

    @Override
    public List<ItemExtDto> readItems(String version) {
        Map<String, ItemExtDto> map = getMap(version, DataKeyEnum.Item, new TypeReference<FileDto<ItemExtDto>>() {});
        List<ItemExtDto> list = new ArrayList<>();
        for (Map.Entry<String, ItemExtDto> entry : map.entrySet()) {
            ItemExtDto itemExtDto = entry.getValue();
            itemExtDto.setId(Integer.parseInt(entry.getKey()));
            list.add(itemExtDto);
        }
        return list;
    }

    @Override
    public List<ImageDto> readMaps(String version) {
        List<MapDto> maps = getValues(version, DataKeyEnum.Map, new TypeReference<FileDto<MapDto>>() {});
        List<ImageDto> images = new ArrayList<>();
        for (MapDto map : maps) {
            ImageDto image = map.image;
            image.setRelatedId(map.MapId);
            images.add(image);
        }
        return images;
    }

    @Override
    public List<RuneExtDto> readRunes(String version) {
        String jsonStr = getJsonStr(version, DataKeyEnum.Rune);
        return JSON.parseArray(jsonStr, RuneExtDto.class);
    }

    @Override
    public List<ImageDto> readProfileIcons(String version) {
        List<ProfileIconDto> icons = getValues(version, DataKeyEnum.ProfileIcon, new TypeReference<FileDto<ProfileIconDto>>() {});
        List<ImageDto> images = new ArrayList<>();
        for (ProfileIconDto iconDto : icons) {
            ImageDto image = iconDto.getImage();
            image.setRelatedId(iconDto.getId());
            image.setId(null);
            images.add(image);
        }
        return images;
    }

    @Override
    public List<SpellDto> readSummonerSpells(String version) {
        return getValues(version, DataKeyEnum.SummonerSpell, new TypeReference<FileDto<SpellDto>>() {});
    }

    private <T> List<T> getValues(String version, DataKeyEnum key, TypeReference<FileDto<T>> typeReference) {
        return new ArrayList<>(getMap(version, key, typeReference).values());
    }

    private <T> Map<String, T> getMap(String version, DataKeyEnum key, TypeReference<FileDto<T>> typeReference) {
        return JSON.parseObject(getJsonStr(version, key), typeReference).getData();
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

    /**
     * Bean for the whole json file.
     */
    @Data
    private static class FileDto<T> {
        private Map<String, T> data;
    }

    /**
     * Bean for map object.
     */
    @Data
    private static class MapDto {
        private Integer MapId;
        private ImageDto image;
    }

    /**
     * Bean for profile icon object.
     */
    @Data
    private static class ProfileIconDto {
        private Integer id;
        private ImageDto image;
    }
}
