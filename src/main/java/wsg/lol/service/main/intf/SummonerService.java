package wsg.lol.service.main.intf;

import wsg.lol.common.pojo.dmo.summoner.SummonerDmo;

/**
 * wsg
 *
 * @author EastSunrise
 */
public interface SummonerService {

    SummonerDmo getSummonerById(String id);
}
