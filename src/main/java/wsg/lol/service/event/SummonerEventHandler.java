package wsg.lol.service.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wsg.lol.common.base.Result;
import wsg.lol.common.enums.system.EventTypeEnum;
import wsg.lol.common.pojo.domain.system.EventDo;
import wsg.lol.service.intf.SummonerService;

/**
 * Handler for events of type {@link EventTypeEnum#Summoner}
 *
 * @author Kingen
 */
@Service(value = "SummonerEventHandler")
public class SummonerEventHandler implements EventHandler {

    private SummonerService summonerService;

    @Override
    public Result handle(EventDo event) {
        return summonerService.addSummoner(event.getContext());
    }

    @Autowired
    public void setSummonerService(SummonerService summonerService) {
        this.summonerService = summonerService;
    }
}
