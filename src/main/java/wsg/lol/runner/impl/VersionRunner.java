package wsg.lol.runner.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import wsg.lol.common.utils.DateUtil;
import wsg.lol.common.utils.LogUtil;
import wsg.lol.dao.config.ApiKey;
import wsg.lol.dao.config.FileConfig;
import wsg.lol.pojo.base.BaseResult;
import wsg.lol.service.version.intf.VersionService;

/**
 * wsg
 *
 * @author wangsigen
 */
@Component
public class VersionRunner implements ApplicationRunner {

    private FileConfig fileConfig;

    private VersionService versionService;

    @Override
    public void run(ApplicationArguments args) {
        String currentVersion;
        do {
            DateUtil.threadSleep(DateUtil.ONE_SECOND);
            currentVersion = ApiKey.getCurrentVersion();
        } while (currentVersion == null);
        if (!currentVersion.equals(fileConfig.getLatestVersion())) {
            LogUtil.info("Start to switch the version from " + currentVersion + " to " + fileConfig.getLatestVersion());
            BaseResult result;
            do {
                result = versionService.initLib();
            } while (!result.isSuccess());
            do {
                result = versionService.updateLib();
            } while (!result.isSuccess());
            // wsg update current version.
            LogUtil.info("Success to switch the version.");
        }
    }

    @Autowired
    public void setFileConfig(FileConfig fileConfig) {
        this.fileConfig = fileConfig;
    }

    @Autowired
    public void setVersionService(VersionService versionService) {
        this.versionService = versionService;
    }
}
