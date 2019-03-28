package wsg.lol.scheduler.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import wsg.lol.common.utils.DateUtil;
import wsg.lol.common.utils.FileUtil;
import wsg.lol.dao.config.StateConfig;
import wsg.lol.pojo.base.BaseResult;
import wsg.lol.pojo.result.VersionResult;
import wsg.lol.scheduler.intf.Scheduler;
import wsg.lol.service.version.intf.VersionService;

import java.io.File;

/**
 * wsg
 *
 * @author wangsigen
 */
@Component
public class SchedulerImpl implements Scheduler {

    private static Logger logger = LoggerFactory.getLogger(Scheduler.class);

    private StateConfig stateConfig;

    private VersionService versionService;

    @Scheduled(fixedRate = DateUtil.ONE_MINUTE)
    public void checkVersion() {
        VersionResult versionResult = versionService.getVersion();
        if (!versionResult.isLatestVersion()) {
            String latestVersion = versionResult.getLatestVersion();
            if (hasDataDragon(latestVersion)) {
                logger.info("Update the version from " + versionResult.getCurrentVersion() + " to " + latestVersion);
                BaseResult result;
                do {
                    result = versionService.updateVersion(latestVersion);
                } while (!result.isSuccess());
            }
        }
    }

    private boolean hasDataDragon(String latestVersion) {
        boolean ret = new File(FileUtil.concat2Path(stateConfig.getDataDir(), latestVersion)).exists();
        if (!ret) {
            logger.info("The data of the latest version " + latestVersion + " don't exist. Please update the data " +
                    "dragon manually.");
        }
        return ret;
    }

    @Autowired
    public void setVersionService(VersionService versionService) {
        this.versionService = versionService;
    }

    @Autowired
    public void setStateConfig(StateConfig stateConfig) {
        this.stateConfig = stateConfig;
    }
}
