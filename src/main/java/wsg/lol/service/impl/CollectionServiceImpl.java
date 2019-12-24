package wsg.lol.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wsg.lol.common.enums.share.ImageGroupEnum;
import wsg.lol.common.pojo.domain.item.*;
import wsg.lol.common.pojo.dto.item.ImageDto;
import wsg.lol.common.pojo.dto.item.ItemExtDto;
import wsg.lol.common.pojo.dto.item.RuneExtDto;
import wsg.lol.common.pojo.transfer.ObjectTransfer;
import wsg.lol.dao.dragon.intf.DragonDao;
import wsg.lol.dao.mybatis.mapper.lol.item.ItemMapper;
import wsg.lol.dao.mybatis.mapper.lol.item.ItemStatsMapper;
import wsg.lol.dao.mybatis.mapper.lol.rune.RuneMapper;
import wsg.lol.dao.mybatis.mapper.lol.rune.RuneTreeMapper;
import wsg.lol.dao.mybatis.mapper.lol.system.ImageMapper;
import wsg.lol.service.base.BaseService;
import wsg.lol.service.intf.CollectionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Kingen
 */
@Service
public class CollectionServiceImpl extends BaseService implements CollectionService {

    private static final Logger logger = LoggerFactory.getLogger(CollectionService.class);

    private DragonDao dragonDao;

    private ItemMapper itemMapper;

    private ItemStatsMapper itemStatsMapper;

    private RuneMapper runeMapper;

    private RuneTreeMapper runeTreeMapper;

    private ImageMapper imageMapper;

    @Override
    @Transactional
    public void updateItems(String version) {
        logger.info("Updating the data of items.");
        List<ItemExtDto> itemExtDtoList = dragonDao.readItems(version);

        logger.info("Updating the items.");
        List<ItemDo> items = ObjectTransfer.transferDtoList(new ArrayList<>(itemExtDtoList), ItemDo.class);
        clear(itemMapper);
        insertList(itemMapper, items);

        logger.info("Updating the stats of items.");
        List<ItemStatsDo> itemStatsDoList = new ArrayList<>();
        for (ItemExtDto itemExtDto : itemExtDtoList) {
            ItemStatsDo statsDo = ObjectTransfer.transferDto(itemExtDto.getStats(), ItemStatsDo.class);
            statsDo.setItemId(itemExtDto.getId());
            itemStatsDoList.add(statsDo);
        }
        clear(itemStatsMapper);
        insertList(itemStatsMapper, itemStatsDoList);

        logger.info("Updating the images of items.");
        List<ImageDto> imageDtoList = new ArrayList<>();
        for (ItemExtDto itemExtDto : itemExtDtoList) {
            ImageDto image = itemExtDto.getImage();
            image.setRelatedId(itemExtDto.getId());
            imageDtoList.add(image);
        }
        updateImages(imageDtoList, ImageGroupEnum.Item);

        logger.info("Succeed in updating the data of items.");
    }

    @Override
    @Transactional
    public void updateRunes(String version) {
        logger.info("Updating the data of runes.");
        List<RuneExtDto> runeExtDtoList = dragonDao.readRunes(version);

        logger.info("Updating the rune trees.");
        List<RuneTreeDo> runeTreeDoList = ObjectTransfer.transferDtoList(new ArrayList<>(runeExtDtoList), RuneTreeDo.class);
        clear(runeTreeMapper);
        insertList(runeTreeMapper, runeTreeDoList);

        logger.info("Updating the runes.");
        List<RuneDo> runeDoList = new ArrayList<>();
        for (RuneExtDto runeExtDto : runeExtDtoList) {
            runeDoList.addAll(runeExtDto.getRunes());
        }
        clear(runeMapper);
        insertList(runeMapper, runeDoList);

        logger.info("Succeed in updating the data of runes.");
    }

    @Override
    @Transactional
    public void updateMaps(String version) {
        logger.info("Updating the images of maps.");
        Map<Integer, ImageDto> map = dragonDao.readMaps(version);
        List<ImageDto> images = new ArrayList<>();
        for (Map.Entry<Integer, ImageDto> entry : map.entrySet()) {
            ImageDto image = entry.getValue();
            image.setRelatedId(entry.getKey());
            images.add(image);
        }
        updateImages(images, ImageGroupEnum.Map);
        logger.info("Succeed in updating the data of maps.");
    }

    @Override
    @Transactional
    public void updateProfileIcons(String version) {
        logger.info("Updating the images of profile icons.");
        Map<Integer, ImageDto> map = dragonDao.readProfileIcons(version);
        List<ImageDto> images = new ArrayList<>();
        for (Map.Entry<Integer, ImageDto> entry : map.entrySet()) {
            ImageDto image = entry.getValue();
            image.setRelatedId(entry.getKey());
            images.add(image);
        }
        updateImages(images, ImageGroupEnum.ProfileIcon);

        logger.info("Succeed in updating the data of profile icons.");
    }

    @Override
    @Transactional
    public void updateImages(List<ImageDto> images, ImageGroupEnum... groups) {
        int count;
        for (ImageGroupEnum group : groups) {
            count = imageMapper.deleteByGroup(group);
            logger.info("Deleted " + count + " images of " + group);
        }

        insertList(imageMapper, ObjectTransfer.transferDtoList(images, ImageDo.class));
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
