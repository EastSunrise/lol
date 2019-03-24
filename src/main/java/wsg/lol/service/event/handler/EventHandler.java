package wsg.lol.service.event.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import wsg.lol.pojo.event.SummonerEvent;
import wsg.lol.service.service.intf.EventService;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-21 14:55
 */
@Component
public class EventHandler {

    @Autowired
    private EventService eventService;

    @EventListener
    public void onApplicationEvent(SummonerEvent summonerEvent) {
        eventService.saveSummonerEvent(summonerEvent);
    }
}
