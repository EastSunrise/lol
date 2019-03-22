package wsg.lol.service.service.intf;

import wsg.lol.pojo.base.BaseResult;
import wsg.lol.pojo.event.SummonerEvent;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-21 16:13
 */
public interface EventService {

    BaseResult saveSummonerEvent(SummonerEvent summonerEvent);
}
