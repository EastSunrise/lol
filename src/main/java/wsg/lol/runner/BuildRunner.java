package wsg.lol.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import wsg.lol.common.base.GenericResult;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.service.intf.LeagueService;
import wsg.lol.service.intf.SystemService;

/**
 * Runner to build the base data. Run only when initializing the database.
 *
 * @author Kingen
 */
@Service
@Order(value = 2)
public class BuildRunner implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(BuildRunner.class);

    private SystemService systemService;

    private LeagueService leagueService;

    /**
     * Initial events of summoners by querying the league entries of all queues, tiers and divisions.
     */
    @Override
    public void run(ApplicationArguments args) {
        logger.info("Initializing the database...");
        GenericResult<Boolean> result = systemService.isInitialized();
        if (result.getObject()) {
            logger.info("The database has been isInitialized.");
            return;
        }

        ResultUtils.assertSuccess(leagueService.initializeByLeagues());
        ResultUtils.assertSuccess(systemService.initialize());

        logger.info("Succeed in initializing the database.");
    }

    @Autowired
    public void setLeagueService(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @Autowired
    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }
}
