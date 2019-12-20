package wsg.lol.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wsg.lol.common.annotation.Platform;
import wsg.lol.common.base.Result;
import wsg.lol.common.enums.share.ImageGroupEnum;
import wsg.lol.common.pojo.domain.item.*;
import wsg.lol.common.pojo.dto.item.ImageDto;
import wsg.lol.common.pojo.dto.item.ItemExtDto;
import wsg.lol.common.pojo.dto.item.RuneExtDto;
import wsg.lol.common.pojo.transfer.ObjectTransfer;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.dao.dragon.intf.DragonDao;
import wsg.lol.dao.mybatis.mapper.lol.item.ItemMapper;
import wsg.lol.dao.mybatis.mapper.lol.item.ItemStatsMapper;
import wsg.lol.dao.mybatis.mapper.lol.rune.RuneMapper;
import wsg.lol.dao.mybatis.mapper.lol.rune.RuneTreeMapper;
import wsg.lol.dao.mybatis.mapper.lol.system.ImageMapper;
import wsg.lol.service.common.ServiceExecutor;
import wsg.lol.service.intf.CollectionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Kingen
 */
@Service
public class CollectionServiceImpl implements CollectionService {

    private static final Logger logger = LoggerFactory.getLogger(CollectionService.class);

    private DragonDao dragonDao;

    private ItemMapper itemMapper;

    private ItemStatsMapper itemStatsMapper;

    private RuneMapper runeMapper;

    private RuneTreeMapper runeTreeMapper;

    private ImageMapper imageMapper;

    @Override
    @Platform
    @Transactional
    public Result updateItems(String version) {
        logger.info("Updating the data of items.");
        List<ItemExtDto> itemExtDtoList = dragonDao.readItems(version);

        logger.info("Updating the items.");
        List<ItemDo> items = ObjectTransfer.transferDtoList(new ArrayList<>(itemExtDtoList), ItemDo.class);
        ServiceExecutor.updateStatic(itemMapper, items).assertSuccess();

        logger.info("Updating the stats of items.");
        List<ItemStatsDo> itemStatsDoList = new ArrayList<>();
        for (ItemExtDto itemExtDto : itemExtDtoList) {
            ItemStatsDo statsDo = ObjectTransfer.transferDto(itemExtDto.getStats(), ItemStatsDo.class);
            statsDo.setItemId(itemExtDto.getId());
            itemStatsDoList.add(statsDo);
        }
        ServiceExecutor.updateStatic(itemStatsMapper, itemStatsDoList).assertSuccess();

        logger.info("Updating the images of items.");
        List<ImageDto> imageDtoList = new ArrayList<>();
        for (ItemExtDto itemExtDto : itemExtDtoList) {
            ImageDto image = itemExtDto.getImage();
            image.setRelatedId(itemExtDto.getId());
            imageDtoList.add(image);
        }
        updateImages(imageDtoList, ImageGroupEnum.Item).assertSuccess();

        logger.info("Succeed in updating the data of items.");
        return ResultUtils.success();
    }

    @Override
    @Platform
    @Transactional
    public Result updateRunes(String version) {
        logger.info("Updating the data of runes.");
        List<RuneExtDto> runeExtDtoList = dragonDao.readRunes(version);

        logger.info("Updating the rune trees.");
        List<RuneTreeDo> runeTreeDoList = ObjectTransfer.transferDtoList(new ArrayList<>(runeExtDtoList), RuneTreeDo.class);
        ServiceExecutor.updateStatic(runeTreeMapper, runeTreeDoList).assertSuccess();

        logger.info("Updating the runes.");
        List<RuneDo> runeDoList = new ArrayList<>();
        for (RuneExtDto runeExtDto : runeExtDtoList) {
            runeDoList.addAll(runeExtDto.getRunes());
        }
        ServiceExecutor.updateStatic(runeMapper, runeDoList).assertSuccess();

        logger.info("Succeed in updating the data of runes.");
        return ResultUtils.success();
    }

    @Override
    @Platform
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
        updateImages(images, ImageGroupEnum.Map).assertSuccess();
        logger.info("Succeed in updating the data of maps.");
        return ResultUtils.success();
    }

    @Override
    @Platform
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
        updateImages(images, ImageGroupEnum.ProfileIcon).assertSuccess();

        logger.info("Succeed in updating the data of profile icons.");
        return ResultUtils.success();
    }

    @Override
    @Platform
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

        ServiceExecutor.insertList(imageMapper, ObjectTransfer.transferDtoList(images, ImageDo.class)).assertSuccess();
        return ResultUtils.success();
    }

    @Autowired
    public void setDragonDao(DragonDao dragonDao) {
        this.dragonDao = dragonDao;
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
    public void setRuneMapper(RuneMapper runeMapper) {
        this.runeMapper = runeMapper;
    }

    @Autowired
    public void setRuneTreeMapper(RuneTreeMapper runeTreeMapper) {
        this.runeTreeMapper = runeTreeMapper;
    }

    @Autowired
    public void setImageMapper(ImageMapper imageMapper) {
        this.imageMapper = imageMapper;
    }
}
