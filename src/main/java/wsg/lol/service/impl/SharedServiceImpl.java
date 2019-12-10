package wsg.lol.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wsg.lol.common.annotation.Performance;
import wsg.lol.common.base.GenericResult;
import wsg.lol.common.base.Result;
import wsg.lol.common.constant.ConfigConst;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.common.enums.share.ImageGroupEnum;
import wsg.lol.common.enums.system.RegionEnum;
import wsg.lol.common.pojo.domain.share.ImageDo;
import wsg.lol.common.pojo.dto.champion.ChampionRotationDto;
import wsg.lol.common.pojo.dto.share.ImageDto;
import wsg.lol.common.pojo.dto.share.ShardStatus;
import wsg.lol.common.pojo.dto.spectator.FeaturedGames;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.dao.api.impl.ChampionV3;
import wsg.lol.dao.api.impl.LOLStatusV3;
import wsg.lol.dao.api.impl.SpectatorV4;
import wsg.lol.dao.common.transfer.ObjectTransfer;
import wsg.lol.dao.dragon.intf.DragonDao;
import wsg.lol.dao.mybatis.mapper.lol.system.ConfigMapper;
import wsg.lol.dao.mybatis.mapper.lol.system.ImageMapper;
import wsg.lol.service.common.MapperExecutor;
import wsg.lol.service.intf.SharedService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Kingen
 */
@Service
public class SharedServiceImpl implements SharedService {

    private static final Logger logger = LoggerFactory.getLogger(SharedService.class);

    private LOLStatusV3 lolStatusV3;

    private ChampionV3 championV3;

    private ConfigMapper configMapper;

    private ImageMapper imageMapper;

    private DragonDao dragonDao;

    private SpectatorV4 spectatorV4;

    @Override
    @Transactional
    public Result updateImages(List<ImageDto> images, ImageGroupEnum... groups) {
        if (CollectionUtils.isEmpty(images)) {
            logger.info("Images is empty. Nothing updated.");
            return ResultUtils.success();
        }

        int count;
        for (ImageGroupEnum group : groups) {
            count = imageMapper.deleteByGroup(group);
            logger.info("Deleted " + count + " images of " + group);
        }

        ResultUtils.assertSuccess(MapperExecutor.insertList(imageMapper, ObjectTransfer.transferDtoList(images, ImageDo.class)));
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
    @Transactional
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

    @Override
    @Performance
    public Result updateSharedStatus() {
        logger.info("Updating the shared status.");
        ShardStatus sharedData = lolStatusV3.getSharedData();
        String value = JSON.toJSONString(sharedData);
        int count = configMapper.updateConfigValue(RegionEnum.LOL, ConfigConst.SHARED_STATUS, value);
        if (count != 1) {
            logger.error("Failed to update the shared status.");
            return ResultUtils.fail(ErrorCodeConst.DATABASE_ERROR, "Failed to update the shared status.");
        }

        logger.info("Succeed in updating the shared status.");
        return ResultUtils.success();
    }

    @Override
    @Performance
    public Result updateChampionRotation() {
        logger.info("Updating the rotation of champions.");
        ChampionRotationDto rotation = championV3.getChampionRotation();
        String value = JSON.toJSONString(rotation);
        int count = configMapper.updateConfigValue(RegionEnum.LOL, ConfigConst.CHAMPION_ROTATION, value);
        if (count != 1) {
            logger.error("Failed to update the rotation of champions.");
            return ResultUtils.fail(ErrorCodeConst.DATABASE_ERROR, "Failed to update the rotation of champions.");
        }

        logger.info("Succeed in updating the rotation of champions.");
        return ResultUtils.success();
    }

    @Override
    @CachePut("featuredGames")
    public GenericResult<FeaturedGames> updateFeaturedGames() {
        GenericResult<FeaturedGames> result = new GenericResult<>();
        result.setObject(spectatorV4.getFeaturedGames());
        return result;
    }

    @Override
    @Cacheable("featuredGames")
    public GenericResult<FeaturedGames> getFeaturedGames() {
        return updateFeaturedGames();
    }

    @Autowired
    public void setSpectatorV4(SpectatorV4 spectatorV4) {
        this.spectatorV4 = spectatorV4;
    }

    @Autowired
    public void setLolStatusV3(LOLStatusV3 lolStatusV3) {
        this.lolStatusV3 = lolStatusV3;
    }

    @Autowired
    public void setChampionV3(ChampionV3 championV3) {
        this.championV3 = championV3;
    }

    @Autowired
    public void setConfigMapper(ConfigMapper configMapper) {
        this.configMapper = configMapper;
    }

    @Autowired
    public void setImageMapper(ImageMapper imageMapper) {
        this.imageMapper = imageMapper;
    }

    @Autowired
    public void setDragonDao(DragonDao dragonDao) {
        this.dragonDao = dragonDao;
    }
}
