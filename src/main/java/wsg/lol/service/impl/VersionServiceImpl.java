package wsg.lol.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wsg.lol.common.base.AppException;
import wsg.lol.common.base.Result;
import wsg.lol.common.constant.ConfigConst;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.common.result.version.VersionResult;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.dao.dragon.intf.DragonDao;
import wsg.lol.dao.dragon.intf.GeneralDao;
import wsg.lol.dao.mybatis.mapper.system.ConfigMapper;
import wsg.lol.service.intf.VersionService;

import java.io.File;

/**
 * @author Kingen
 */
@Service("versionService")
public class VersionServiceImpl implements VersionService {

    private static Logger logger = LoggerFactory.getLogger(VersionService.class);

    private DragonDao dragonDao;

    private GeneralDao generalDao;

    private ConfigMapper configMapper;

    @Override
    public Result checkCdn(String version) {
        String cdnDir = dragonDao.getCdnDir(version);
        boolean exists = new File(cdnDir).exists();
        if (!exists) {
            String message = "Can't find cdn in " + cdnDir + ". Please update the data dragon manually.";
            logger.info(message);
            return ResultUtils.fail(ErrorCodeConst.CDN_NOT_EXIST, message);
        }
        return ResultUtils.success();
    }

    @Override
    public VersionResult getVersion() {
        VersionResult versionResult = new VersionResult();
        versionResult.setCurrentVersion(configMapper.getConfigValue(ConfigConst.CONFIG_NAME_CURRENT_VERSION));
        versionResult.setLatestVersion(generalDao.getLatestVersion());
        return versionResult;
    }

    @Override
    public Result updateVersion(String version) {
        int count = configMapper.updateConfigValue(ConfigConst.CONFIG_NAME_CURRENT_VERSION, version);
        if (1 != count) {
            logger.error("Failed to update the version config.");
            throw new AppException(ErrorCodeConst.DATABASE_ERROR);
        }
        return ResultUtils.success();
    }

    @Autowired
    public void setGeneralDao(GeneralDao generalDao) {
        this.generalDao = generalDao;
    }

    @Autowired
    public void setDragonDao(DragonDao dragonDao) {
        this.dragonDao = dragonDao;
    }

    @Autowired
    public void setConfigMapper(ConfigMapper configMapper) {
        this.configMapper = configMapper;
    }
}
