package wsg.lol.service.impl;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wsg.lol.common.base.Result;
import wsg.lol.common.constant.ConfigConst;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.common.pojo.dto.champion.ChampionRotationDto;
import wsg.lol.common.pojo.dto.share.ShardStatus;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.dao.api.impl.ChampionV3;
import wsg.lol.dao.api.impl.LOLStatusV3;
import wsg.lol.dao.mybatis.mapper.system.ConfigMapper;
import wsg.lol.service.intf.SharedService;

/**
 * @author Kingen
 */
@Service
public class SharedServiceImpl implements SharedService {

    private static final Logger logger = LoggerFactory.getLogger(SharedService.class);

    private LOLStatusV3 lolStatusV3;

    private ChampionV3 championV3;

    private ConfigMapper configMapper;

    @Override
    public Result updateSharedStatus() {
        logger.info("Updating the shared status.");
        ShardStatus sharedData = lolStatusV3.getSharedData();
        String value = JSON.toJSONString(sharedData);
        int count = configMapper.updateConfigValue(ConfigConst.SHARED_STATUS, value);
        if (count != 1) {
            logger.error("Failed to update the shared status.");
            return ResultUtils.fail(ErrorCodeConst.DATABASE_ERROR, "Failed to update the shared status.");
        }

        logger.info("Succeed in updating the shared status.");
        return ResultUtils.success();
    }

    @Override
    public Result updateChampionRotation() {
        logger.info("Updating the rotation of champions.");
        ChampionRotationDto rotation = championV3.getChampionRotation();
        String value = JSON.toJSONString(rotation);
        int count = configMapper.updateConfigValue(ConfigConst.CHAMPION_ROTATION, value);
        if (count != 1) {
            logger.error("Failed to update the rotation of champions.");
            return ResultUtils.fail(ErrorCodeConst.DATABASE_ERROR, "Failed to update the rotation of champions.");
        }

        logger.info("Succeed in updating the rotation of champions.");
        return ResultUtils.success();
    }

    @Autowired
    public void setChampionV3(ChampionV3 championV3) {
        this.championV3 = championV3;
    }

    @Autowired
    public void setLolStatusV3(LOLStatusV3 lolStatusV3) {
        this.lolStatusV3 = lolStatusV3;
    }

    @Autowired
    public void setConfigMapper(ConfigMapper configMapper) {
        this.configMapper = configMapper;
    }
}
