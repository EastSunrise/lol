package wsg.lol.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import wsg.lol.common.base.AppException;
import wsg.lol.common.pojo.dto.summoner.SummonerDto;
import wsg.lol.common.pojo.dto.system.VersionDto;
import wsg.lol.common.util.PageUtils;
import wsg.lol.service.base.BaseService;
import wsg.lol.service.intf.*;

import java.util.List;

/**
 * @author Kingen
 */
@Service
public class ScheduleServiceImpl extends BaseService implements ScheduleService {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleService.class);

    private SystemService systemService;

    private SharedService sharedService;

    private ChampionService championService;

    private CollectionService collectionService;

    private SummonerService summonerService;

    private TransactionTemplate transactionTemplate;

    @Override
    public void checkVersion() {
        logger.info("Checking the version...");
        VersionDto versionDto = systemService.getVersion();
        if (versionDto.isLatestVersion()) {
            logger.info("The version is latest.");
            return;
        }

        String version = versionDto.getLatestVersion();
        if (!systemService.checkCdn(version)) {
            logger.error("Can't find cdn for " + version + ". Please update the data dragon manually.");
            return;
        }

        try {
            transactionTemplate.execute(transactionStatus -> {
                logger.info("Updating the version from {} to {}...", versionDto.getCurrentVersion(), version);
                championService.updateChampions(version);
                collectionService.updateItems(version);
                collectionService.updateMaps(version);
                collectionService.updateRunes(version);
                collectionService.updateProfileIcons(version);
                championService.updateSummonerSpells(version);
                systemService.updateVersion(version);
                return null;
            });
        } catch (AppException e) {
            logger.error(e.getMessage(), e);
            return;
        }

        logger.info("Succeed in updating the version from " + versionDto.getCurrentVersion() + " to " + version);
    }

    @Override
    public void updateSharedData() {
        logger.info("Updating the shared data.");
        sharedService.updateShardStatus();
        sharedService.updateChampionRotation();
    }

    @Override
    public void updateFeaturedGames() {
        logger.info("Updating the featured games.");
        sharedService.updateFeaturedGames();
    }

    @Override
    public void updateSummoners() {
        logger.info("Getting summoners for update...");
        List<? extends SummonerDto> summoners = summonerService.getSummonersForUpdate(PageUtils.DEFAULT_PAGE);
        logger.info("Got {} summoners for update. Handling...", summoners.size());

        int success = 0;
        for (SummonerDto summoner : summoners) {
            String summonerId = summoner.getId();
            try {
                summonerService.updateSummoner(summonerId);
                success++;
            } catch (Exception e) {
                logger.error("Failed to update the summoner {}.", summonerId);
                logger.error(e.getMessage(), e);
            }
        }

        logger.info("Summoners updated, {} succeeded, {} failed.", success, summoners.size() - success);
    }

    @Override
    public void updateMatches() {
        logger.info("Getting summoners for match...");
        List<? extends SummonerDto> summoners = summonerService.getSummonersForMatch(PageUtils.DEFAULT_PAGE);
        logger.info("Got {} summoners for match. Handling...", summoners.size());

        int success = 0;
        for (SummonerDto summoner : summoners) {
            String accountId = summoner.getAccountId();
            try {
                summonerService.updateMatches(accountId, summoner.getLastMatch());
                success++;
            } catch (Exception e) {
                logger.error("Failed to update matches of the account {}.", accountId);
                logger.error(e.getMessage(), e);
            }
        }

        logger.info("Matches updated, {} succeeded, {} failed.", success, summoners.size() - success);
    }

    @Autowired
    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }

    @Autowired
    public void setSharedService(SharedService sharedService) {
        this.sharedService = sharedService;
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
    public void setSummonerService(SummonerService summonerService) {
        this.summonerService = summonerService;
    }

    @Autowired
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }
}
