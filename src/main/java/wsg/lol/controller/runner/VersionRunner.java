package wsg.lol.controller.runner;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import wsg.lol.common.base.AppException;
import wsg.lol.common.base.Result;
import wsg.lol.common.pojo.parser.RecordExtraProcessor;
import wsg.lol.common.result.version.VersionResult;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.service.intf.MessageService;
import wsg.lol.service.intf.VersionService;

import java.util.Map;
import java.util.Set;

/**
 * Runner to update the version to the latest.
 *
 * @author Kingen
 */
@Service
@Order(value = 1)
public class VersionRunner implements ApplicationRunner {

    private static Logger logger = LoggerFactory.getLogger(VersionRunner.class);

    private VersionService versionService;

    private TransactionTemplate transactionTemplate;

    private MessageService messageService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Checking the version...");
        VersionResult versionResult = versionService.getVersion();
        if (versionResult.isLatestVersion()) {
            logger.info("The version is latest.");
            return;
        }

        String version = versionResult.getLatestVersion();
        Result result = versionService.checkCdn(version);
        if (!result.isSuccess()) {
            messageService.sendWarnMessage(result);
            return;
        }

        try {
            transactionTemplate.execute(transactionStatus -> {
                logger.info("Updating the version from " + versionResult.getCurrentVersion() + " to " + version);
                ResultUtils.assertSuccess(versionService.updateChampions(version));
                ResultUtils.assertSuccess(versionService.updateItems(version));
                ResultUtils.assertSuccess(versionService.updateMaps(version));
                ResultUtils.assertSuccess(versionService.updateRunes(version));
                ResultUtils.assertSuccess(versionService.updateProfileIcons(version));
                ResultUtils.assertSuccess(versionService.updateSummonerSpells(version));
                for (Map.Entry<String, Set<Object>> entry : RecordExtraProcessor.getExtraMap().entrySet()) {
                    logger.info("Extra field. Field: {}; value: {}.", entry.getKey(), StringUtils.join(entry.getValue(), ","));
                }
                ResultUtils.assertSuccess(versionService.updateVersion(version));
                return ResultUtils.success();
            });
        } catch (AppException e) {
            logger.error(e.getMessage());
            messageService.sendWarnMessage(ResultUtils.fail(e));
            return;
        }

        logger.info("Succeed in updating the version from " + versionResult.getCurrentVersion() + " to " + version);
    }

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
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
