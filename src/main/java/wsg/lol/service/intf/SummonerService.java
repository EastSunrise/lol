package wsg.lol.service.intf;

import wsg.lol.dmo.summoner.SummonerDmo;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-07 15:35
 */
public interface SummonerService {

    SummonerDmo getSummonerById(String id);
}
