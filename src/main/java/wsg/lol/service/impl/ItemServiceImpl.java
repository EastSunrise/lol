package wsg.lol.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wsg.lol.common.base.Result;
import wsg.lol.common.enums.champion.ImageGroupEnum;
import wsg.lol.common.pojo.dto.general.ImageDto;
import wsg.lol.common.pojo.dto.item.ItemDto;
import wsg.lol.common.pojo.dto.item.ItemExtDto;
import wsg.lol.common.pojo.dto.item.ItemStatsDto;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.dao.dragon.intf.DragonDao;
import wsg.lol.dao.mybatis.common.StaticExecutor;
import wsg.lol.dao.mybatis.mapper.item.ItemMapper;
import wsg.lol.dao.mybatis.mapper.item.ItemStatsMapper;
import wsg.lol.service.intf.ItemService;
import wsg.lol.service.intf.SharedService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kingen
 */
@Service
public class ItemServiceImpl implements ItemService {

    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);

    private DragonDao dragonDao;

    private ItemMapper itemMapper;

    private ItemStatsMapper itemStatsMapper;

    private SharedService sharedService;

    @Override
    @Transactional
    public Result updateItems(String version) {
        logger.info("Updating the data of items.");
        List<ItemExtDto> itemExtDtoList = dragonDao.readItems(version);

        logger.info("Updating the items.");
        List<ItemDto> itemDtoList = new ArrayList<>(itemExtDtoList);
        ResultUtils.assertSuccess(StaticExecutor.updateStatic(itemMapper, itemDtoList));

        logger.info("Updating the stats of items.");
        List<ItemStatsDto> itemStatsDtoList = new ArrayList<>();
        for (ItemExtDto itemExtDto : itemExtDtoList) {
            ItemStatsDto stats = itemExtDto.getStats();
            stats.setItemId(itemExtDto.getId());
            itemStatsDtoList.add(stats);
        }
        ResultUtils.assertSuccess(StaticExecutor.updateStatic(itemStatsMapper, itemStatsDtoList));

        logger.info("Updating the images of items.");
        List<ImageDto> imageDtoList = new ArrayList<>();
        for (ItemExtDto itemExtDto : itemExtDtoList) {
            ImageDto image = itemExtDto.getImage();
            image.setRelatedId(itemExtDto.getId());
            imageDtoList.add(image);
        }
        ResultUtils.assertSuccess(sharedService.updateImages(imageDtoList, ImageGroupEnum.Item));

        logger.info("Succeed in updating the data of items.");
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
    public void setSharedService(SharedService sharedService) {
        this.sharedService = sharedService;
    }
}
