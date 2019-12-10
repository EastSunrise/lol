package wsg.lol.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wsg.lol.common.base.AppException;
import wsg.lol.common.base.GenericResult;
import wsg.lol.common.base.Result;
import wsg.lol.common.constant.ConfigConst;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.common.enums.system.PlatformRoutingEnum;
import wsg.lol.common.result.system.VersionResult;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.config.GlobalConfig;
import wsg.lol.dao.dragon.intf.DragonDao;
import wsg.lol.dao.dragon.intf.GeneralDao;
import wsg.lol.dao.mybatis.mapper.lol.system.ConfigMapper;
import wsg.lol.service.intf.SystemService;

import java.io.File;

/**
 * @author Kingen
 */
@Service
public class SystemServiceImpl implements SystemService {

    private static final Logger logger = LoggerFactory.getLogger(SystemService.class);

    private ConfigMapper configMapper;

    private GeneralDao generalDao;

    private DragonDao dragonDao;

    private GlobalConfig globalConfig;

    @Override
    public GenericResult<Boolean> checkCdn(String version) {
        GenericResult<Boolean> result = new GenericResult<>();
        String cdnDir = dragonDao.getCdnDir(version);
        boolean exists = new File(cdnDir).exists();
        if (!exists) {
            logger.info("Can't find cdn in {}. Please update the data dragon manually.", cdnDir);
            result.setObject(false);
            return result;
        }
        result.setObject(true);
        return result;
    }

    @Override
    public VersionResult getVersion() {
        VersionResult versionResult = new VersionResult();
        versionResult.setCurrentVersion(configMapper.getConfigValue(ConfigConst.CURRENT_VERSION, PlatformRoutingEnum.LOL));
        versionResult.setLatestVersion(generalDao.getLatestVersion());
        return versionResult;
    }

    @Override
    public Result updateVersion(String version) {
        int count = configMapper.updateConfigValue(PlatformRoutingEnum.LOL, ConfigConst.CURRENT_VERSION, version);
        if (1 != count) {
            logger.error("Failed to update the version config.");
            throw new AppException(ErrorCodeConst.DATABASE_ERROR);
        }
        return ResultUtils.success();
    }

    @Override
    public void sendMessage(String message) {
        // todo send a message.
        ResultUtils.success();
    }

    @Override
    public void sendWarnMessage(Result result) {
        if (!result.isSuccess()) {
            sendMessage(result.getMessage());
        }
    }

    @Override
    public GenericResult<Boolean> initialized() {
        String value = configMapper.getConfigValue(ConfigConst.IS_DATABASE_INITIALIZED, globalConfig.getRegion());
        GenericResult<Boolean> result = new GenericResult<>();
        result.setObject(Boolean.parseBoolean(value));
        return result;
    }

    @Override
    public Result initialize() {
        int count = configMapper.updateConfigValue(globalConfig.getRegion(), ConfigConst.IS_DATABASE_INITIALIZED, String.valueOf(true));
        if (1 != count) {
            logger.error("Failed to update the initialization config.");
            throw new AppException(ErrorCodeConst.DATABASE_ERROR);
        }
        return ResultUtils.success();
    }

    @Autowired
    public void setGlobalConfig(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
    }

    @Autowired
    public void setConfigMapper(ConfigMapper configMapper) {
        this.configMapper = configMapper;
    }

    @Autowired
    public void setGeneralDao(GeneralDao generalDao) {
        this.generalDao = generalDao;
    }

    @Autowired
    public void setDragonDao(DragonDao dragonDao) {
        this.dragonDao = dragonDao;
    }
}
