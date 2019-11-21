package wsg.lol.service.impl;

import com.alibaba.fastjson.JSONArray;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wsg.lol.common.base.AppException;
import wsg.lol.common.base.Result;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.common.enums.champion.ChampionTipEnum;
import wsg.lol.common.enums.champion.ImageGroupEnum;
import wsg.lol.common.enums.champion.SpellNumEnum;
import wsg.lol.common.pojo.dto.champion.*;
import wsg.lol.common.pojo.dto.general.ImageDto;
import wsg.lol.common.pojo.dto.item.BlockDto;
import wsg.lol.common.pojo.dto.item.RecommendedDto;
import wsg.lol.common.pojo.dto.item.RecommendedExtDto;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.dao.dragon.intf.DragonDao;
import wsg.lol.dao.mybatis.common.StaticExecutor;
import wsg.lol.dao.mybatis.mapper.champion.*;
import wsg.lol.dao.mybatis.mapper.item.BlockMapper;
import wsg.lol.dao.mybatis.mapper.item.RecommendedMapper;
import wsg.lol.service.intf.ChampionService;
import wsg.lol.service.intf.SharedService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kingen
 */
@Service
public class ChampionServiceImpl implements ChampionService {

    private static final Logger logger = LoggerFactory.getLogger(ChampionService.class);

    private DragonDao dragonDao;

    private ChampionMapper championMapper;

    private SkinMapper skinMapper;

    private ChampionTipMapper championTipMapper;

    private ChampionStatsMapper championStatsMapper;

    private RecommendedMapper recommendedMapper;

    private BlockMapper blockMapper;

    private SpellMapper spellMapper;

    private SharedService sharedService;

    @Override
    @Transactional
    public Result updateChampions(String version) {
        logger.info("Updating the data of champions.");
        List<ChampionExtDto> championExtDtoList = dragonDao.readChampions(version);

        logger.info("Updating the champions.");
        List<ChampionDto> championDtoList = new ArrayList<>(championExtDtoList);
        ResultUtils.assertSuccess(StaticExecutor.updateStatic(championMapper, championDtoList));

        logger.info("Updating the images of champions.");
        List<ImageDto> imageDtoList = new ArrayList<>();
        for (ChampionExtDto championExtDto : championExtDtoList) {
            ImageDto image = championExtDto.getImage();
            image.setRelatedId(championExtDto.getId());
            imageDtoList.add(image);
        }
        ResultUtils.assertSuccess(sharedService.updateImages(imageDtoList, ImageGroupEnum.Champion));

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
        ResultUtils.assertSuccess(StaticExecutor.updateStatic(skinMapper, skinDtoList));

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
        ResultUtils.assertSuccess(StaticExecutor.updateStatic(championTipMapper, championTipDtoList));

        logger.info("Updating the stats of champions.");
        List<ChampionStatsDto> statsDtoList = new ArrayList<>();
        for (ChampionExtDto championExtDto : championExtDtoList) {
            ChampionStatsDto stats = championExtDto.getStats();
            stats.setChampionId(championExtDto.getId());
            statsDtoList.add(stats);
        }
        ResultUtils.assertSuccess(StaticExecutor.updateStatic(championStatsMapper, statsDtoList));

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
        ResultUtils.assertSuccess(this.updateSpells(spellDtoList,
                SpellNumEnum.P, SpellNumEnum.Q, SpellNumEnum.W, SpellNumEnum.E, SpellNumEnum.R
        ));
        logger.info("Updating the images of champion spells.");
        ResultUtils.assertSuccess(sharedService.updateImages(imageDtoList, ImageGroupEnum.Spell, ImageGroupEnum.Passive));

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
        ResultUtils.assertSuccess(StaticExecutor.updateStatic(recommendedMapper, recommendedDtoList));
        ResultUtils.assertSuccess(StaticExecutor.updateStatic(blockMapper, blockDtoList));

        logger.info("Succeed in updating the data of champions.");
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
        ResultUtils.assertSuccess(this.updateSpells(spellDtoList, SpellNumEnum.S));
        logger.info("Updating the images of summoner spells.");
        ResultUtils.assertSuccess(sharedService.updateImages(imageDtoList, ImageGroupEnum.SummonerSpell));

        logger.info("Succeed in updating the data of summoner spells.");
        return ResultUtils.success();
    }

    // TODO: (Kingen, 2019/11/21) 事务嵌套
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
    public void setDragonDao(DragonDao dragonDao) {
        this.dragonDao = dragonDao;
    }

    @Autowired
    public void setChampionMapper(ChampionMapper championMapper) {
        this.championMapper = championMapper;
    }

    @Autowired
    public void setSkinMapper(SkinMapper skinMapper) {
        this.skinMapper = skinMapper;
    }

    @Autowired
    public void setChampionTipMapper(ChampionTipMapper championTipMapper) {
        this.championTipMapper = championTipMapper;
    }

    @Autowired
    public void setChampionStatsMapper(ChampionStatsMapper championStatsMapper) {
        this.championStatsMapper = championStatsMapper;
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
    public void setSpellMapper(SpellMapper spellMapper) {
        this.spellMapper = spellMapper;
    }

    @Autowired
    public void setSharedService(SharedService sharedService) {
        this.sharedService = sharedService;
    }
}