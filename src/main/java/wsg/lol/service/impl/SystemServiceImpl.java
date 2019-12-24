package wsg.lol.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wsg.lol.common.base.AppException;
import wsg.lol.common.constant.ConfigConst;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.common.pojo.dto.system.VersionDto;
import wsg.lol.dao.dragon.intf.DragonDao;
import wsg.lol.dao.dragon.intf.GeneralDao;
import wsg.lol.dao.mybatis.mapper.lol.system.ConfigMapper;
import wsg.lol.service.base.BaseService;
import wsg.lol.service.intf.SystemService;

import java.io.File;

/**
 * @author Kingen
 */
@Service
public class SystemServiceImpl extends BaseService implements SystemService {

    private static final Logger logger = LoggerFactory.getLogger(SystemService.class);

    private ConfigMapper configMapper;

    private GeneralDao generalDao;

    private DragonDao dragonDao;

    @Override
    public boolean checkCdn(String version) {
        String cdnDir = dragonDao.getCdnDir(version);
        boolean exists = new File(cdnDir).exists();
        if (!exists) {
            logger.info("Can't find cdn in {}. Please update the data dragon manually.", cdnDir);
            return false;
        }
        return true;
    }

    @Override
    public VersionDto getVersion() {
        VersionDto versionDto = new VersionDto();
        versionDto.setCurrentVersion(configMapper.getConfigValue(ConfigConst.CURRENT_VERSION));
        versionDto.setLatestVersion(generalDao.getLatestVersion());
        return versionDto;
    }

    @Override
    public void updateVersion(String version) {
        int count = configMapper.updateConfigValue(ConfigConst.CURRENT_VERSION, version);
        if (1 != count) {
            logger.error("Failed to update the version config.");
            throw new AppException(ErrorCodeConst.DATABASE_ERROR);
        }
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
