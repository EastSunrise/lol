package wsg.lol.service.version.impl;

import com.alibaba.fastjson.JSONArray;
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
import wsg.lol.common.pojo.dto.recommend.BlockDto;
import wsg.lol.common.pojo.dto.recommend.RecommendedDto;
import wsg.lol.common.pojo.dto.recommend.RecommendedExtDto;
import wsg.lol.common.pojo.dto.rune.RuneDto;
import wsg.lol.common.pojo.dto.rune.RuneExtDto;
import wsg.lol.common.pojo.dto.rune.RuneTreeDto;
import wsg.lol.common.result.version.VersionResult;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.dao.dragon.intf.DragonDao;
import wsg.lol.dao.dragon.intf.GeneralDao;
import wsg.lol.dao.mybatis.common.StaticStrategy;
import wsg.lol.dao.mybatis.mapper.champion.*;
import wsg.lol.dao.mybatis.mapper.item.BlockMapper;
import wsg.lol.dao.mybatis.mapper.item.ItemMapper;
import wsg.lol.dao.mybatis.mapper.item.ItemStatsMapper;
import wsg.lol.dao.mybatis.mapper.item.RecommendedMapper;
import wsg.lol.dao.mybatis.mapper.rune.RuneMapper;
import wsg.lol.dao.mybatis.mapper.rune.RuneTreeMapper;
import wsg.lol.dao.mybatis.mapper.system.ConfigMapper;
import wsg.lol.dao.mybatis.mapper.system.ImageMapper;
import wsg.lol.service.version.intf.VersionService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    private SpellMapper spellMapper;

    private ItemMapper itemMapper;

    private ItemStatsMapper itemStatsMapper;

    private ChampionTipMapper championTipMapper;

    private RuneMapper runeMapper;

    private RuneTreeMapper runeTreeMapper;

    private RecommendedMapper recommendedMapper;

    private BlockMapper blockMapper;

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
        ResultUtils.assertSuccess(updateStatic(championMapper, championDtoList));

        logger.info("Updating the images of champions.");
        List<ImageDto> imageDtoList = new ArrayList<>();
        for (ChampionExtDto championExtDto : championExtDtoList) {
            ImageDto image = championExtDto.getImage();
            image.setRelatedId(championExtDto.getId());
            imageDtoList.add(image);
        }
        ResultUtils.assertSuccess(updateImages(imageDtoList, ImageGroupEnum.Champion));

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
        ResultUtils.assertSuccess(updateStatic(skinMapper, skinDtoList));

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
        ResultUtils.assertSuccess(updateStatic(championTipMapper, championTipDtoList));

        logger.info("Updating the stats of champions.");
        List<ChampionStatsDto> statsDtoList = new ArrayList<>();
        for (ChampionExtDto championExtDto : championExtDtoList) {
            ChampionStatsDto stats = championExtDto.getStats();
            stats.setChampionId(championExtDto.getId());
            statsDtoList.add(stats);
        }
        ResultUtils.assertSuccess(updateStatic(championStatsMapper, statsDtoList));

        logger.info("Updating the spells of champions.");
        List<SpellDto> spellDtoList = new ArrayList<>();
        imageDtoList = new ArrayList<>();
        SpellNumEnum[] enums = new SpellNumEnum[]{
                SpellNumEnum.Q, SpellNumEnum.W, SpellNumEnum.E, SpellNumEnum.R
        };
        for (ChampionExtDto championExtDto : championExtDtoList) {
            List<SpellDto> spells = championExtDto.getSpells();
            Integer id = championExtDto.getId();
            for (int i = 0; i < spells.size(); i++) {
                SpellDto spell = spells.get(i);
                spell.setChampionId(id);
                spell.setNum(enums[i]);
                spell.setKey(SpellDto.calcKey(id, enums[i]));

                ImageDto image = spell.getImage();
                image.setRelatedId(spell.getKey());
                imageDtoList.add(image);
            }
            spellDtoList.addAll(spells);

            SpellDto passive = championExtDto.getPassive();
            passive.setChampionId(id);
            passive.setNum(SpellNumEnum.P);
            passive.setId(championExtDto.getKey() + SpellNumEnum.P.name());
            passive.setKey(SpellDto.calcKey(id, SpellNumEnum.P));
            spellDtoList.add(passive);
            ImageDto image = passive.getImage();
            image.setRelatedId(passive.getKey());
            imageDtoList.add(image);
        }
        for (SpellDto spellDto : spellDtoList) {
            if (spellDto.getKey() == null) {
                logger.error(spellDto.getId());
            }
        }
        ResultUtils.assertSuccess(updateSpells(spellDtoList,
                SpellNumEnum.P, SpellNumEnum.Q, SpellNumEnum.W, SpellNumEnum.E, SpellNumEnum.R
        ));
        logger.info("Updating the images of champion spells.");
        ResultUtils.assertSuccess(updateImages(imageDtoList, ImageGroupEnum.Spell, ImageGroupEnum.Passive));

        logger.info("Updating the recommended items of champions.");
        List<RecommendedDto> recommendedDtoList = new ArrayList<>();
        List<BlockDto> blockDtoList = new ArrayList<>();
        int max = 0;
        for (ChampionExtDto championExtDto : championExtDtoList) {
            List<RecommendedExtDto> recommendedExtDtoList = championExtDto.getRecommended();
            for (int i = 0; i < recommendedExtDtoList.size(); i++) {
                RecommendedExtDto recommendedExtDto = recommendedExtDtoList.get(i);
                Integer generateId = RecommendedDto.generateId(championExtDto.getId(), i);
                recommendedExtDto.setId(generateId);
                recommendedDtoList.add(recommendedExtDto);

                for (BlockDto block : recommendedExtDto.getBlocks()) {
                    block.setRecommendedId(generateId);
                    blockDtoList.add(block);
                    int length = JSONArray.toJSONString(block.getItems()).length();
                    if (length > max) {
                        max = length;
                    }
                }
            }
        }
        ResultUtils.assertSuccess(updateStatic(recommendedMapper, recommendedDtoList));
        ResultUtils.assertSuccess(updateStatic(blockMapper, blockDtoList));

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
        ResultUtils.assertSuccess(updateStatic(itemMapper, itemDtoList));

        logger.info("Updating the stats of items.");
        List<ItemStatsDto> itemStatsDtoList = new ArrayList<>();
        for (ItemExtDto itemExtDto : itemExtDtoList) {
            ItemStatsDto stats = itemExtDto.getStats();
            stats.setItemId(itemExtDto.getId());
            itemStatsDtoList.add(stats);
        }
        ResultUtils.assertSuccess(updateStatic(itemStatsMapper, itemStatsDtoList));

        logger.info("Updating the images of items.");
        List<ImageDto> imageDtoList = new ArrayList<>();
        for (ItemExtDto itemExtDto : itemExtDtoList) {
            ImageDto image = itemExtDto.getImage();
            image.setRelatedId(itemExtDto.getId());
            imageDtoList.add(image);
        }
        ResultUtils.assertSuccess(updateImages(imageDtoList, ImageGroupEnum.Item));

        logger.info("Succeed in updating the data of items.");
        return ResultUtils.success();
    }

    @Override
    @Transactional
    public Result updateRunes(String version) {
        logger.info("Updating the data of runes.");
        List<RuneExtDto> runeExtDtoList = dragonDao.readRunes(version);

        logger.info("Updating the rune trees.");
        List<RuneTreeDto> runeTreeDtoList = new ArrayList<>(runeExtDtoList);
        ResultUtils.assertSuccess(updateStatic(runeTreeMapper, runeTreeDtoList));

        logger.info("Updating the runes.");
        List<RuneDto> runeDtoList = new ArrayList<>();
        for (RuneExtDto runeExtDto : runeExtDtoList) {
            List<Map<String, List<RuneDto>>> slots = runeExtDto.getSlots();
            int id = runeExtDto.getId();
            for (int i = 0; i < slots.size(); i++) {
                Map<String, List<RuneDto>> slot = slots.get(i);
                List<RuneDto> runes = slot.get(RuneExtDto.RUNES);
                for (int j = 0; j < runes.size(); j++) {
                    RuneDto runeDto = runes.get(j);
                    runeDto.setTreeId(id);
                    runeDto.setNumX(i);
                    runeDto.setNumY(j);
                    runeDtoList.add(runeDto);
                }
            }
        }
        ResultUtils.assertSuccess(updateStatic(runeMapper, runeDtoList));

        logger.info("Succeed in updating the data of runes.");
        return ResultUtils.success();
    }

    @Override
    @Transactional
    public Result updateSummonerSpells(String version) {
        logger.info("Updating the summoner spells.");
        List<SpellDto> spellDtoList = dragonDao.readSummonerSpells(version);
        List<ImageDto> imageDtoList = new ArrayList<>();
        for (SpellDto spellDto : spellDtoList) {
            spellDto.setNum(SpellNumEnum.S);
            ImageDto image = spellDto.getImage();
            image.setRelatedId(spellDto.getKey());
            image.setGroup(ImageGroupEnum.SummonerSpell);
            imageDtoList.add(image);
        }
        ResultUtils.assertSuccess(updateSpells(spellDtoList, SpellNumEnum.S));
        logger.info("Updating the images of summoner spells.");
        ResultUtils.assertSuccess(updateImages(imageDtoList, ImageGroupEnum.SummonerSpell));

        logger.info("Succeed in updating the data of summoner spells.");
        return ResultUtils.success();
    }

    @Override
    @Transactional
    public Result updateMaps(String version) {
        logger.info("Updating the images of maps.");
        Map<Integer, ImageDto> map = dragonDao.readMaps(version);
        List<ImageDto> images = new ArrayList<>();
        for (Map.Entry<Integer, ImageDto> entry : map.entrySet()) {
            ImageDto image = entry.getValue();
            image.setRelatedId(entry.getKey());
            images.add(image);
        }
        ResultUtils.assertSuccess(updateImages(images, ImageGroupEnum.Map));

        logger.info("Succeed in updating the data of maps.");
        return ResultUtils.success();
    }

    @Override
    public Result updateProfileIcons(String version) {
        logger.info("Updating the images of profile icons.");
        Map<Integer, ImageDto> map = dragonDao.readProfileIcons(version);
        List<ImageDto> images = new ArrayList<>();
        for (Map.Entry<Integer, ImageDto> entry : map.entrySet()) {
            ImageDto image = entry.getValue();
            image.setRelatedId(entry.getKey());
            images.add(image);
        }
        ResultUtils.assertSuccess(updateImages(images, ImageGroupEnum.ProfileIcon));

        logger.info("Succeed in updating the data of profile icons.");
        return ResultUtils.success();
    }

    private <T> Result updateStatic(StaticStrategy<T> strategy, List<T> data) {
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
            logger.info("Images is empty. Nothing updated.");
            return ResultUtils.success();
        }

        int count;
        for (ImageGroupEnum group : groups) {
            count = imageMapper.deleteByGroup(group);
            logger.info("Deleted " + count + " images of " + group);
        }

        count = imageMapper.batchInsert(imageDtoList);
        if (count != imageDtoList.size()) {
            logger.error("Failed to insert images of " + StringUtils.join(groups, ", "));
            throw new AppException(ErrorCodeConst.DATABASE_ERROR);
        }
        logger.info("Inserted " + count + " images of " + StringUtils.join(groups, ", "));
        return ResultUtils.success();
    }

    private Result updateSpells(List<SpellDto> spells, SpellNumEnum... nums) {
        if (CollectionUtils.isEmpty(spells)) {
            logger.info("Spells is empty. Nothing updated.");
            return ResultUtils.success();
        }

        int count;
        for (SpellNumEnum num : nums) {
            count = spellMapper.deleteByNum(num);
            logger.info("Deleted " + count + " spells of " + num);
        }

        count = spellMapper.batchInsert(spells);
        if (count != spells.size()) {
            logger.error("Failed to insert spells of " + StringUtils.join(nums, ", "));
            throw new AppException(ErrorCodeConst.DATABASE_ERROR);
        }
        logger.info("Inserted " + count + " spells of " + StringUtils.join(nums, ", "));
        return ResultUtils.success();
    }

    @Autowired
    public void setRecommendedMapper(RecommendedMapper recommendedMapper) {
        this.recommendedMapper = recommendedMapper;
    }

    @Autowired
    public void setBlockMapper(BlockMapper blockMapper) {
        this.blockMapper = blockMapper;
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
    public void setSpellMapper(SpellMapper spellMapper) {
        this.spellMapper = spellMapper;
    }
}
