package wsg.lol.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wsg.lol.common.base.Result;
import wsg.lol.common.enums.share.ImageGroupEnum;
import wsg.lol.common.pojo.domain.share.ImageDo;
import wsg.lol.common.pojo.dto.share.ImageDto;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.dao.common.transfer.ObjectTransfer;
import wsg.lol.dao.dragon.intf.DragonDao;
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

    private ImageMapper imageMapper;

    private DragonDao dragonDao;

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

    @Autowired
    public void setImageMapper(ImageMapper imageMapper) {
        this.imageMapper = imageMapper;
    }

    @Autowired
    public void setDragonDao(DragonDao dragonDao) {
        this.dragonDao = dragonDao;
    }
}
