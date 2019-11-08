package wsg.lol.controller.version;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import wsg.lol.common.intf.Scheduler;
import wsg.lol.common.pojo.base.BaseResult;
import wsg.lol.common.result.version.VersionResult;
import wsg.lol.service.version.intf.VersionService;

import java.io.File;

/**
 * // TODO: (wangsigen, 2019/11/8)
 *
 * @author wangsigen
 * @date 2019/11/8
 * @since 2.4.9.3
 */
@Service
public class VersionTask implements Scheduler {

    private static Logger logger = LoggerFactory.getLogger(VersionTask.class);

    private VersionService versionService;

    @Value("${state.dir.data}")
    private String dataDir;

    @Scheduled(fixedRate = DateUtils.MILLIS_PER_DAY)
    public void checkVersion() {
        logger.info("Start to check the version.");
        VersionResult versionResult = versionService.getVersion();
        if (!versionResult.isLatestVersion()) {
            String latestVersion = versionResult.getLatestVersion();
            boolean exists = new File(StringUtils.join(dataDir, "/", latestVersion)).exists();
            if (!exists) {
                logger.info("The data of the latest version " + latestVersion + " don't exist. Please update the data dragon manually.");
            } else {
                // TODO: (wangsigen, 2019/11/8) shutdown the main thread

                // update the version
                logger.info("Start to update the version from " + versionResult.getCurrentVersion() + " to " + latestVersion);
                BaseResult result = versionService.updateVersion(latestVersion);
                if (!result.isSuccess()) {
                    logger.info("Failed to updated the version: " + result.getErrorMsg());
                } else {
                    logger.info("Success to update the version from " + versionResult.getCurrentVersion() + " to " + latestVersion);
                }

                // TODO: (wangsigen, 2019/11/8) start the main thread
            }
        }
    }

    @Autowired
    public void setVersionService(VersionService versionService) {
        this.versionService = versionService;
    }
}
