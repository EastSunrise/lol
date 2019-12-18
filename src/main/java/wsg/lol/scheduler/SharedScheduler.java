package wsg.lol.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import wsg.lol.common.base.AppException;
import wsg.lol.common.base.GenericResult;
import wsg.lol.common.result.system.VersionResult;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.service.intf.ChampionService;
import wsg.lol.service.intf.CollectionService;
import wsg.lol.service.intf.SharedService;
import wsg.lol.service.intf.SystemService;

/**
 * Scheduler for shared data.
 *
 * @author Kingen
 */
@Component
public class SharedScheduler {

    private static final Logger logger = LoggerFactory.getLogger(SharedScheduler.class);

    private TransactionTemplate transactionTemplate;

    private ChampionService championService;

    private CollectionService collectionService;

    private SystemService systemService;

    private SharedService sharedService;

    /**
     * Check the version. Update the static data from the dragon if it's not the latest version.
     */
    @Scheduled(cron = "${cron.share.version}")
    public void checkVersion() {
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
                championService.updateChampions(version).assertSuccess();
                collectionService.updateItems(version).assertSuccess();
                collectionService.updateMaps(version).assertSuccess();
                collectionService.updateRunes(version).assertSuccess();
                collectionService.updateProfileIcons(version).assertSuccess();
                championService.updateSummonerSpells(version).assertSuccess();
                systemService.updateVersion(version).assertSuccess();
                return ResultUtils.success();
            });
        } catch (AppException e) {
            logger.error(e.getMessage(), e);
            return;
        }

        logger.info("Succeed in updating the version from " + versionResult.getCurrentVersion() + " to " + version);
    }

    /**
     * Update the shard status and the rotation of champions.
     * Request 2.
     */
    @Scheduled(cron = "${cron.share.shard}")
    public void updateSharedData() {
        logger.info("Scheduling to update the shared data.");
        sharedService.updateShardStatus();
        sharedService.updateChampionRotation();
    }

    /**
     * Update the featured games.
     * Request 1.
     */
    @Scheduled(cron = "${cron.share.featured}")
    public void updateFeaturedGames() {
        logger.info("Scheduling to update the featured games.");
        sharedService.updateFeaturedGames();
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
    public void setSharedService(SharedService sharedService) {
        this.sharedService = sharedService;
    }

    @Autowired
    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }
}
