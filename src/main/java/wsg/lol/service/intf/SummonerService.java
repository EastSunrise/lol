package wsg.lol.service.intf;

import wsg.lol.common.base.Result;

/**
 * Service for summoners.
 *
 * @author Kingen
 */
public interface SummonerService {

    /**
     * Update the data of summoner spells once the version changes.
     */
    Result updateSummonerSpells(String version);
}
