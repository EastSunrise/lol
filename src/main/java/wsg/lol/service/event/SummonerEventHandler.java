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
import wsg.lol.service.intf.SummonerService;

import javax.xml.ws.http.HTTPException;
import java.util.List;

/**
 * Handler for events of type {@link EventTypeEnum#Summoner}
 *
 * @author Kingen
 */
@Service(value = "SummonerEventHandler")
public class SummonerEventHandler implements EventHandler {

    private static final Logger logger = LoggerFactory.getLogger(SummonerEventHandler.class);

    private TransactionTemplate transactionTemplate;

    private SummonerService summonerService;

    private EventService eventService;

    @Override
    @Performance
    public Result handle(List<? extends EventDo> events) {
        final int[] success = {0};
        for (EventDo event : events) {
            String summonerId = event.getId();
            try {
                transactionTemplate.execute(transactionStatus -> {
                    Result result = summonerService.addSummoner(summonerId);
                    ResultUtils.assertSuccess(result);

                    result = eventService.updateStatus(EventTypeEnum.Summoner, summonerId, EventStatusEnum.Unfinished, EventStatusEnum.Finished);
                    ResultUtils.assertSuccess(result);
                    logger.info("Succeed in handling the event of {}.", summonerId);
                    success[0]++;
                    return ResultUtils.success();
                });
            } catch (HTTPException | AppException e) {
                logger.error("{}: Failed to handle the event of the summoner {}", e.getMessage(), summonerId);
                eventService.updateStatus(EventTypeEnum.Summoner, summonerId, EventStatusEnum.Unfinished, EventStatusEnum.Finishing);
            } catch (RuntimeException e) {
                logger.error("Failed to handle the event of the summoner {}", summonerId);
                e.printStackTrace();
                eventService.updateStatus(EventTypeEnum.Summoner, summonerId, EventStatusEnum.Unfinished, EventStatusEnum.Finishing);
            }
        }

        logger.info("Events of summoners done, {} succeeded, {} failed.", success[0], events.size() - success[0]);
        return ResultUtils.success();
    }

    @Autowired
    public void setSummonerService(SummonerService summonerService) {
        this.summonerService = summonerService;
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
