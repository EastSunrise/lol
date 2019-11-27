package wsg.lol.service.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import wsg.lol.common.annotation.Performance;
import wsg.lol.common.base.AppException;
import wsg.lol.common.base.Result;
import wsg.lol.common.enums.system.EventTypeEnum;
import wsg.lol.common.pojo.dto.system.EventDto;
import wsg.lol.common.util.ResultUtils;

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

    @Override
    @Performance
    public synchronized Result handle(List<EventDto> events) {
        for (EventDto event : events) {
            Long gameId = Long.parseLong(event.getContext());
            try {
                transactionTemplate.execute(transactionStatus -> {
                    logger.info("Handling the event of {}.", gameId);

                    logger.info("Succeed in handling the event of {}.", gameId);
                    return ResultUtils.success();
                });
            } catch (AppException e) {
                e.printStackTrace();
            }
        }

        logger.info("Succeed in handling the events of summoner id.");
        return ResultUtils.success();
    }
}
