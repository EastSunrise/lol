package wsg.lol.service.version.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wsg.lol.common.base.AppException;
import wsg.lol.common.base.GenericResult;
import wsg.lol.common.base.Result;
import wsg.lol.common.constant.ConfigConst;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.common.enums.champion.ChampionTipEnum;
import wsg.lol.common.enums.champion.ImageGroupEnum;
import wsg.lol.common.enums.champion.SpellNumEnum;
import wsg.lol.common.pojo.dto.champion.*;
import wsg.lol.common.pojo.dto.general.ImageDto;
import wsg.lol.common.pojo.dto.item.ItemDto;
import wsg.lol.common.pojo.dto.item.ItemExtDto;
import wsg.lol.common.pojo.dto.item.ItemStatsDto;
import wsg.lol.common.pojo.dto.others.MapDto;
import wsg.lol.common.pojo.dto.rune.RuneDto;
import wsg.lol.common.pojo.dto.rune.RuneExtDto;
import wsg.lol.common.pojo.dto.rune.RuneTreeDto;
import wsg.lol.common.result.version.VersionResult;
import wsg.lol.common.util.AssertUtils;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.dao.dragon.intf.DragonDao;
import wsg.lol.dao.dragon.intf.GeneralDao;
import wsg.lol.dao.mybatis.common.StaticStrategy;
import wsg.lol.dao.mybatis.mapper.champion.*;
import wsg.lol.dao.mybatis.mapper.item.ItemMapper;
import wsg.lol.dao.mybatis.mapper.item.ItemStatsMapper;
import wsg.lol.dao.mybatis.mapper.rune.RuneMapper;
import wsg.lol.dao.mybatis.mapper.rune.RuneTreeMapper;
import wsg.lol.dao.mybatis.mapper.system.ConfigMapper;
import wsg.lol.dao.mybatis.mapper.system.ImageMapper;
import wsg.lol.service.version.intf.VersionService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kingen
 */
@Service("versionService")
public class VersionServiceImpl implements VersionService {

    private static Logger logger = LoggerFactory.getLogger(VersionService.class);

    private DragonDao dragonDao;

    private GeneralDao generalDao;

    private ConfigMapper configMapper;

    private ChampionMapper championMapper;

    private ImageMapper imageMapper;

    private SkinMapper skinMapper;

    private ChampionStatsMapper championStatsMapper;

    private ChampionSpellMapper championSpellMapper;

    private ItemMapper itemMapper;

    private ItemStatsMapper itemStatsMapper;

    private ChampionTipMapper championTipMapper;

    private RuneMapper runeMapper;

    private RuneTreeMapper runeTreeMapper;

    @Override
    public GenericResult<Boolean> checkCdn(String version) {
        String cdnDir = dragonDao.getCdnDir(version);
        boolean exists = new File(cdnDir).exists();
        if (!exists) {
            logger.info("Can't find cdn in " + cdnDir + ". Please update the data dragon manually.");
        }
        GenericResult<Boolean> result = new GenericResult<>();
        result.setObject(exists);
        return result;
    }

    @Override
    public VersionResult getVersion() {
        VersionResult versionResult = new VersionResult();
        versionResult.setCurrentVersion(configMapper.getConfigValue(ConfigConst.CONFIG_NAME_CURRENT_VERSION));
        versionResult.setLatestVersion(generalDao.getLatestVersion());
        return versionResult;
    }

    @Override
    public Result updateVersion(String version) {
        int count = configMapper.updateConfigValue(ConfigConst.CONFIG_NAME_CURRENT_VERSION, version);
        if (1 != count) {
            logger.error("Failed to update the version config.");
            throw new AppException(ErrorCodeConst.DATABASE_ERROR);
        }
        return ResultUtils.success();
    }

    @Override
    @Transactional
    public Result updateChampions(String version) {
        logger.info("Updating the data of champions.");
        List<ChampionExtDto> championExtDtoList = dragonDao.readChampions(version);

        logger.info("Updating the champions.");
        List<ChampionDto> championDtoList = new ArrayList<>(championExtDtoList);
        AssertUtils.isSuccess(updateState(championMapper, championDtoList));

        logger.info("Updating the images of champions.");
        List<ImageDto> imageDtoList = new ArrayList<>();
        for (ChampionExtDto championExtDto : championExtDtoList) {
            ImageDto image = championExtDto.getImage();
            image.setRelatedId(championExtDto.getId());
            imageDtoList.add(image);
        }
        AssertUtils.isSuccess(updateImages(imageDtoList, ImageGroupEnum.Champion));

        logger.info("Updating the skins.");
        List<SkinDto> skinDtoList = new ArrayList<>();
        for (ChampionExtDto championExtDto : championExtDtoList) {
            List<SkinDto> skins = championExtDto.getSkins();
            Integer id = championExtDto.getId();
            for (SkinDto skin : skins) {
                skin.setChampionId(id);
            }
            skinDtoList.addAll(skins);
        }
        AssertUtils.isSuccess(updateState(skinMapper, skinDtoList));

        logger.info("Updating the tips of champions.");
        List<ChampionTipDto> championTipDtoList = new ArrayList<>();
        for (ChampionExtDto championExtDto : championExtDtoList) {
            Integer id = championExtDto.getId();
            for (String tip : championExtDto.getAllytips()) {
                ChampionTipDto championTipDto = new ChampionTipDto();
                championTipDto.setChampionId(id);
                championTipDto.setTip(tip);
                championTipDto.setType(ChampionTipEnum.Ally);
                championTipDtoList.add(championTipDto);
            }
            for (String tip : championExtDto.getEnemytips()) {
                ChampionTipDto championTipDto = new ChampionTipDto();
                championTipDto.setChampionId(id);
                championTipDto.setTip(tip);
                championTipDto.setType(ChampionTipEnum.Enemy);
                championTipDtoList.add(championTipDto);
            }
        }
        AssertUtils.isSuccess(updateState(championTipMapper, championTipDtoList));

        logger.info("Updating the stats of champions.");
        List<ChampionStatsDto> statsDtoList = new ArrayList<>();
        for (ChampionExtDto championExtDto : championExtDtoList) {
            ChampionStatsDto stats = championExtDto.getStats();
            stats.setChampionId(championExtDto.getId());
            statsDtoList.add(stats);
        }
        AssertUtils.isSuccess(updateState(championStatsMapper, statsDtoList));

        logger.info("Updating the spells of champions.");
        List<ChampionSpellDto> spellDtoList = new ArrayList<>();
        imageDtoList = new ArrayList<>();
        SpellNumEnum[] enums = new SpellNumEnum[]{
                SpellNumEnum.Q, SpellNumEnum.W, SpellNumEnum.E, SpellNumEnum.R
        };
        for (ChampionExtDto championExtDto : championExtDtoList) {
            List<ChampionSpellDto> spells = championExtDto.getSpells();
            Integer id = championExtDto.getId();
            for (int i = 0; i < spells.size(); i++) {
                ChampionSpellDto spell = spells.get(i);
                spell.setChampionId(id);
                spell.setNum(enums[i]);
                ImageDto image = spell.getImage();
                image.setRelatedId(spell.hashCode());
                imageDtoList.add(image);
            }
            spellDtoList.addAll(spells);

            ChampionSpellDto passive = championExtDto.getPassive();
            passive.setChampionId(id);
            passive.setNum(SpellNumEnum.Passive);
            passive.setId(championExtDto.getKey() + "Passive");
            spellDtoList.add(passive);
            ImageDto image = passive.getImage();
            image.setRelatedId(passive.hashCode());// TODO: (Kingen, 2019/11/18)
            imageDtoList.add(image);
        }
        AssertUtils.isSuccess(updateState(championSpellMapper, spellDtoList));
        logger.info("Updating the images of champion spells.");
        AssertUtils.isSuccess(updateImages(imageDtoList, ImageGroupEnum.Spell, ImageGroupEnum.Passive));

        logger.info("Succeed in updating the data of champions.");
        return ResultUtils.success();
    }

    @Override
    @Transactional
    public Result updateItems(String version) {
        logger.info("Updating the data of items.");
        List<ItemExtDto> itemExtDtoList = dragonDao.readItems(version);

        logger.info("Updating the items.");
        List<ItemDto> itemDtoList = new ArrayList<>(itemExtDtoList);
        AssertUtils.isSuccess(updateState(itemMapper, itemDtoList));

        logger.info("Updating the stats of items.");
        List<ItemStatsDto> itemStatsDtoList = new ArrayList<>();
        for (ItemExtDto itemExtDto : itemExtDtoList) {
            ItemStatsDto stats = itemExtDto.getStats();
            stats.setItemId(itemExtDto.getId());
            itemStatsDtoList.add(stats);
        }
        AssertUtils.isSuccess(updateState(itemStatsMapper, itemStatsDtoList));

        logger.info("Updating the images of items.");
        List<ImageDto> imageDtoList = new ArrayList<>();
        for (ItemExtDto itemExtDto : itemExtDtoList) {
            ImageDto image = itemExtDto.getImage();
            image.setRelatedId(itemExtDto.getId());
            imageDtoList.add(image);
        }
        AssertUtils.isSuccess(updateImages(imageDtoList, ImageGroupEnum.Item));

        return ResultUtils.success();
    }

    @Override
    @Transactional
    public Result updateRunes(String version) {
        logger.info("Updating the data of runes.");
        List<RuneExtDto> runeExtDtoList = dragonDao.readRunes(version);

        logger.info("Updating the rune trees.");
        List<RuneTreeDto> runeTreeDtoList = new ArrayList<>(runeExtDtoList);
        AssertUtils.isSuccess(updateState(runeTreeMapper, runeTreeDtoList));

        logger.info("Updating the runes.");
        List<RuneDto> runeDtoList = new ArrayList<>();
        for (RuneExtDto runeExtDto : runeExtDtoList) {
            RuneDto[][] slots = runeExtDto.getSlots();
            int id = runeExtDto.getId();
            for (int i = 0; i < slots.length; i++) {
                RuneDto[] slot = slots[i];
                for (int j = 0; j < slot.length; j++) {
                    RuneDto runeDto = slot[j];
                    runeDto.setTreeId(id);
                    runeDto.setNumX(i);
                    runeDto.setNumY(j);
                    runeDtoList.add(runeDto);
                }
            }
        }
        AssertUtils.isSuccess(updateState(runeMapper, runeDtoList));

        return ResultUtils.success();
    }

    @Override
    @Transactional
    public Result updateSummonerSpells(String version) {
        logger.info("Start to update the data of summoner spells.");
        return ResultUtils.success();
    }

    @Override
    @Transactional
    public Result updateMaps(String version) {
        logger.info("Updating the images of maps.");
        List<MapDto> mapDtoList = dragonDao.readMaps(version);
        List<ImageDto> imageDtoList = new ArrayList<>();
        for (MapDto mapDto : mapDtoList) {
            ImageDto image = mapDto.getImage();
            image.setRelatedId(mapDto.getMap().getMapId());
            imageDtoList.add(image);
        }
        AssertUtils.isSuccess(updateImages(imageDtoList, ImageGroupEnum.Map));

        return ResultUtils.success();
    }

    private <T> Result updateState(StaticStrategy<T> strategy, List<T> data) {
        if (CollectionUtils.isEmpty(data)) {
            logger.info("Collection is empty. Nothing updated.");
            return ResultUtils.success();
        }

        int count = strategy.clear();
        logger.info(count + " Cleared.");
        count = strategy.batchInsert(data);
        if (count != data.size()) {
            logger.error("Failed to insert the data");
            throw new AppException(ErrorCodeConst.DATABASE_ERROR);
        }
        logger.info(count + " Inserted.");
        return ResultUtils.success();
    }

    private Result updateImages(List<ImageDto> imageDtoList, ImageGroupEnum... groups) {
        if (CollectionUtils.isEmpty(imageDtoList)) {
            logger.info("Collection is empty. Nothing updated.");
            return ResultUtils.success();
        }

        int count;
        for (ImageGroupEnum group : groups) {
            count = imageMapper.deleteByGroup(group);
            logger.info("Deleted " + count + " images of " + group);
        }

        count = imageMapper.batchInsert(imageDtoList);
        if (count != imageDtoList.size()) {
            logger.error("Failed to insert the images of " + StringUtils.join(groups, ", "));
            throw new AppException(ErrorCodeConst.DATABASE_ERROR);
        }
        logger.info("Inserted " + count + " images of " + StringUtils.join(groups, ", "));
        return ResultUtils.success();
    }

    @Autowired
    public void setRuneMapper(RuneMapper runeMapper) {
        this.runeMapper = runeMapper;
    }

    @Autowired
    public void setRuneTreeMapper(RuneTreeMapper runeTreeMapper) {
        this.runeTreeMapper = runeTreeMapper;
    }

    @Autowired
    public void setChampionTipMapper(ChampionTipMapper championTipMapper) {
        this.championTipMapper = championTipMapper;
    }

    @Autowired
    public void setItemMapper(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    @Autowired
    public void setItemStatsMapper(ItemStatsMapper itemStatsMapper) {
        this.itemStatsMapper = itemStatsMapper;
    }

    @Autowired
    public void setGeneralDao(GeneralDao generalDao) {
        this.generalDao = generalDao;
    }

    @Autowired
    public void setDragonDao(DragonDao dragonDao) {
        this.dragonDao = dragonDao;
    }

    @Autowired
    public void setConfigMapper(ConfigMapper configMapper) {
        this.configMapper = configMapper;
    }

    @Autowired
    public void setChampionMapper(ChampionMapper championMapper) {
        this.championMapper = championMapper;
    }

    @Autowired
    public void setImageMapper(ImageMapper imageMapper) {
        this.imageMapper = imageMapper;
    }

    @Autowired
    public void setSkinMapper(SkinMapper skinMapper) {
        this.skinMapper = skinMapper;
    }

    @Autowired
    public void setChampionStatsMapper(ChampionStatsMapper championStatsMapper) {
        this.championStatsMapper = championStatsMapper;
    }

    @Autowired
    public void setChampionSpellMapper(ChampionSpellMapper championSpellMapper) {
        this.championSpellMapper = championSpellMapper;
    }
}
