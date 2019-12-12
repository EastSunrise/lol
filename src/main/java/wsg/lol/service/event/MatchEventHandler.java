package wsg.lol.service.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import wsg.lol.common.annotation.Performance;
import wsg.lol.common.base.AppException;
import wsg.lol.common.base.Result;
import wsg.lol.common.enums.system.EventStatusEnum;
import wsg.lol.common.enums.system.EventTypeEnum;
import wsg.lol.common.pojo.domain.system.EventDo;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.service.intf.EventService;
import wsg.lol.service.intf.MatchService;

import java.util.List;

/**
 * Handler for events of type {@link EventTypeEnum#Match}
 *
 * @author Kingen
 */
@Service("MatchEventHandler")
public class MatchEventHandler implements EventHandler {

    private static final Logger logger = LoggerFactory.getLogger(MatchEventHandler.class);

    private TransactionTemplate transactionTemplate;

    private MatchService matchService;

    private EventService eventService;

    @Override
    @Performance
    public Result handle(List<? extends EventDo> events) {
        final int[] success = {0};
        for (EventDo event : events) {
            long gameId = Long.parseLong(event.getId());
            try {
                transactionTemplate.execute(transactionStatus -> {
                    ResultUtils.assertSuccess(matchService.addMatch(gameId));

                    Result result = eventService.updateStatus(EventTypeEnum.Match, gameId, EventStatusEnum.Unfinished, EventStatusEnum.Finished);
                    ResultUtils.assertSuccess(result);
                    logger.info("Succeed in handling the event of {}.", gameId);
                    success[0]++;
                    return ResultUtils.success();
                });
            } catch (AppException e) {
                logger.error("Failed to handle the event of the match {}", gameId);
                e.printStackTrace();
            }
        }

        logger.info("Events of matches done, {} succeeded, {} failed.", success[0], events.size() - success[0]);
        return ResultUtils.success();
    }

    @Autowired
    public void setMatchService(MatchService matchService) {
        this.matchService = matchService;
    }

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }
}
