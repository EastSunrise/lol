package wsg.lol.service.impl;

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
import wsg.lol.common.enums.champion.ImageGroupEnum;
import wsg.lol.common.pojo.dto.general.ImageDto;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.dao.dragon.intf.DragonDao;
import wsg.lol.dao.mybatis.mapper.system.ImageMapper;
import wsg.lol.service.intf.SystemService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Kingen
 */
@Service
public class SystemServiceImpl implements SystemService {

    private static final Logger logger = LoggerFactory.getLogger(SystemService.class);

    private ImageMapper imageMapper;

    private DragonDao dragonDao;

    // TODO: (Kingen, 2019/11/21) 事务嵌套失效
    @Override
    @Transactional
    public Result updateImages(List<ImageDto> imageDtoList, ImageGroupEnum... groups) {
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
}
