package wsg.lol.service.intf;

import wsg.lol.common.base.Result;

/**
 * Service for champions.
 *
 * @author Kingen
 */
public interface ChampionService {

    /**
     * Update the data of champions once the version changes.
     */
    Result updateChampions(String version);

    /**
     * Update the data of summoner spells once the version changes.
     */
    Result updateSummonerSpells(String version);
}
