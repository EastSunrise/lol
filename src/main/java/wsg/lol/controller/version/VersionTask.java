package wsg.lol.controller.version;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import wsg.lol.common.base.AppException;
import wsg.lol.common.base.GenericResult;
import wsg.lol.common.result.version.VersionResult;
import wsg.lol.common.util.AssertUtils;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.service.version.intf.VersionService;

/**
 * // TODO: (Kingen, 2019/11/8)
 *
 * @author Kingen
 * @date 2019/11/8
 * @since 2.4.9.3
 */
@Service
public class VersionTask implements ApplicationRunner {

    private static Logger logger = LoggerFactory.getLogger(VersionTask.class);

    private VersionService versionService;

    private TransactionTemplate transactionTemplate;

    @Value("${dragon.dir.cdn}")
    private String cdnDir;

    @Scheduled(fixedRate = DateUtils.MILLIS_PER_DAY)
    public void checkVersion() {
        logger.info("Checking the version...");
        VersionResult versionResult = versionService.getVersion();
        if (versionResult.isLatestVersion()) {
            logger.info("The version is latest.");
            return;
        }

        logger.info("The latest version is " + versionResult.getLatestVersion() + ". Please update the version.");
    }

    /**
     * update the version if not the latest.
     */
    @Override
    public void run(ApplicationArguments args) {
        logger.info("Checking the version...");
        VersionResult versionResult = versionService.getVersion();
        if (versionResult.isLatestVersion()) {
            logger.info("The version is latest.");
            return;
        }

        String version = versionResult.getLatestVersion();
        GenericResult<Boolean> cdnExists = versionService.checkCdn(version);
        if (!cdnExists.getObject()) {
            return;
        }

        try {
            transactionTemplate.execute(transactionStatus -> {
                logger.info("Updating the version from " + versionResult.getCurrentVersion() + " to " + version);
                AssertUtils.isSuccess(versionService.updateChampions(version));
                AssertUtils.isSuccess(versionService.updateItems(version));
                AssertUtils.isSuccess(versionService.updateMaps(version));
                AssertUtils.isSuccess(versionService.updateRunes(version));
//                AssertUtils.isSuccess(versionService.updateSummonerSpells(version));
                AssertUtils.isSuccess(versionService.updateVersion(version));
                return ResultUtils.success();
            });
        } catch (AppException e) {
            logger.error(e.getMessage());
            return;
        }

        logger.info("Succeed in updating the version from " + versionResult.getCurrentVersion() + " to " + version);
    }

    @Autowired
    public void setVersionService(VersionService versionService) {
        this.versionService = versionService;
    }

    @Autowired
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }
}
