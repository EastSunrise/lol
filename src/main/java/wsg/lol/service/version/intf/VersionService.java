package wsg.lol.service.version.intf;

import wsg.lol.pojo.base.BaseResult;

/**
 * wsg
 *
 * @author wangsigen
 */
public interface VersionService {

    /**
     * clear all old data.
     */
    BaseResult initLib();

    /**
     * Update the data once the version changes.
     */
    BaseResult updateLib();

    /**
     * Update the data of champions once the version changes.
     */
    BaseResult updateChampionLib();

    /**
     * Update the data of items once the version changes.
     */
    BaseResult updateItemLib();

    /**
     * Update the data of reforged runes once the version changes.
     */
    BaseResult updateRuneLib();

    /**
     * Update the data of summoner spells once the version changes.
     */
    BaseResult updateSummonerSpellLib();

    /**
     * Update the data of maps once the version changes.
     */
    BaseResult updateMapLib();
}
