package wsg.lol.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wsg.lol.common.base.Result;
import wsg.lol.common.enums.share.ImageGroupEnum;
import wsg.lol.common.pojo.domain.share.ItemDo;
import wsg.lol.common.pojo.domain.share.ItemStatsDo;
import wsg.lol.common.pojo.dto.share.ImageDto;
import wsg.lol.common.pojo.dto.share.ItemDto;
import wsg.lol.common.pojo.dto.share.ItemExtDto;
import wsg.lol.common.pojo.transfer.ObjectTransfer;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.dao.dragon.intf.DragonDao;
import wsg.lol.dao.mybatis.mapper.item.ItemMapper;
import wsg.lol.dao.mybatis.mapper.item.ItemStatsMapper;
import wsg.lol.service.common.MapperExecutor;
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
        List<ItemDo> items = ObjectTransfer.transferDtoList(new ArrayList<>(itemExtDtoList), ItemDto.class, ItemDo.class);
        ResultUtils.assertSuccess(MapperExecutor.updateStatic(itemMapper, items));

        logger.info("Updating the stats of items.");
        List<ItemStatsDo> itemStatsDoList = new ArrayList<>();
        for (ItemExtDto itemExtDto : itemExtDtoList) {
            ItemStatsDo statsDo = ObjectTransfer.transferDto(itemExtDto.getStats(), ItemStatsDo.class);
            statsDo.setItemId(itemExtDto.getId());
            itemStatsDoList.add(statsDo);
        }
        ResultUtils.assertSuccess(MapperExecutor.updateStatic(itemStatsMapper, itemStatsDoList));

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
