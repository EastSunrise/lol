package wsg.lol.service.main.intf;

import wsg.lol.common.pojo.dmo.summoner.SummonerDto;

/**
 * wsg
 *
 * @author EastSunrise
 */
public interface SummonerService {

    SummonerDto getSummonerById(String id);
}
