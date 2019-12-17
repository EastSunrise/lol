package wsg.lol.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.transaction.support.TransactionTemplate;
import wsg.lol.common.base.AppException;
import wsg.lol.common.base.GenericResult;
import wsg.lol.common.result.system.VersionResult;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.service.intf.ChampionService;
import wsg.lol.service.intf.CollectionService;
import wsg.lol.service.intf.SystemService;

/**
 * Runner to update the version to the latest.
 *
 * @author Kingen
 */
//@Service
//@Order(value = 1)
public class VersionRunner implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(VersionRunner.class);

    private TransactionTemplate transactionTemplate;

    private ChampionService championService;

    private CollectionService collectionService;

    private SystemService systemService;

    /**
     * Update the static data from the dragon if it's not the latest version.
     */
    @Override
    public void run(ApplicationArguments args) {
        logger.info("Checking the version...");
        VersionResult versionResult = systemService.getVersion();
        if (versionResult.isLatestVersion()) {
            logger.info("The version is latest.");
            return;
        }

        String version = versionResult.getLatestVersion();
        GenericResult<Boolean> result = systemService.checkCdn(version);
        if (!result.getObject()) {
            logger.error("Can't find cdn for " + version + ". Please update the data dragon manually.");
            return;
        }

        try {
            transactionTemplate.execute(transactionStatus -> {
                logger.info("Updating the version from {} to {}...", versionResult.getCurrentVersion(), version);
                ResultUtils.assertSuccess(championService.updateChampions(version));
                ResultUtils.assertSuccess(collectionService.updateItems(version));
                ResultUtils.assertSuccess(collectionService.updateMaps(version));
                ResultUtils.assertSuccess(collectionService.updateRunes(version));
                ResultUtils.assertSuccess(collectionService.updateProfileIcons(version));
                ResultUtils.assertSuccess(championService.updateSummonerSpells(version));
                ResultUtils.assertSuccess(systemService.updateVersion(version));
                return ResultUtils.success();
            });
        } catch (AppException e) {
            logger.error(e.getMessage(), e);
            return;
        }

        logger.info("Succeed in updating the version from " + versionResult.getCurrentVersion() + " to " + version);
    }

    @Autowired
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    @Autowired
    public void setChampionService(ChampionService championService) {
        this.championService = championService;
    }

    @Autowired
    public void setCollectionService(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @Autowired
    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }
}
