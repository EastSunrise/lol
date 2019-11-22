package wsg.lol.service.system.impl;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import wsg.lol.common.base.AppException;
import wsg.lol.common.base.GenericResult;
import wsg.lol.common.base.Result;
import wsg.lol.common.constant.ConfigConst;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.common.enums.system.EventStatusEnum;
import wsg.lol.common.enums.system.EventTypeEnum;
import wsg.lol.common.result.version.VersionResult;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.dao.dragon.intf.DragonDao;
import wsg.lol.dao.dragon.intf.GeneralDao;
import wsg.lol.dao.mybatis.mapper.system.ConfigMapper;
import wsg.lol.dao.mybatis.mapper.system.EventMapper;
import wsg.lol.service.system.intf.EventHandler;
import wsg.lol.service.system.intf.SystemService;

import java.io.File;
import java.util.List;

/**
 * @author Kingen
 */
@Service
public class SystemServiceImpl implements SystemService {

    private static final Logger logger = LoggerFactory.getLogger(SystemService.class);

    private ConfigMapper configMapper;

    private GeneralDao generalDao;

    private DragonDao dragonDao;

    private EventMapper eventMapper;

    private ApplicationContext applicationContext;

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

    @Override
    public void sendMessage(String message) {
        // TODO: (Kingen, 2019/11/21)  send a message.
        ResultUtils.success();
    }

    @Override
    public void sendWarnMessage(Result result) {
        if (!result.isSuccess()) {
            sendMessage(result.getMessage());
        }
    }

    @Override
    public GenericResult<Boolean> isDatabaseInitialized() {
        String value = configMapper.getConfigValue(ConfigConst.IS_DATABASE_INITIALIZED);
        GenericResult<Boolean> result = new GenericResult<>();
        result.setObject(Boolean.parseBoolean(value));
        return result;
    }

    @Override
    public Result handle(EventTypeEnum eventType) {
        List<String> contexts = eventMapper.selectEventContext(eventType, EventStatusEnum.Unfinished);
        if (CollectionUtils.isEmpty(contexts)) {
            return ResultUtils.success();
        }
        return getEventHandler(eventType).handle(contexts);
    }

    private EventHandler getEventHandler(EventTypeEnum eventType) {
        return (EventHandler) applicationContext.getBean(eventType.getEventBeanName());
    }

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Autowired
    public void setEventMapper(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
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
