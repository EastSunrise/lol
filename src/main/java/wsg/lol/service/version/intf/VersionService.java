package wsg.lol.service.version.intf;

import wsg.lol.pojo.base.BaseResult;
import wsg.lol.pojo.result.VersionResult;

/**
 * wsg
 *
 * @author wangsigen
 */
public interface VersionService {

    /**
     * Check if the latest version.
     */
    VersionResult getVersion();

    /**
     * Update the static data ot the certain version.
     */
    BaseResult updateVersion(String version);

    /**
     * Update the data of champions once the version changes.
     */
    BaseResult updateChampionLib(String version);

    /**
     * Update the data of items once the version changes.
     */
    BaseResult updateItemLib(String version);

    /**
     * Update the data of reforged runes once the version changes.
     */
    BaseResult updateRuneLib(String version);

    /**
     * Update the data of summoner spells once the version changes.
     */
    BaseResult updateSummonerSpellLib(String version);

    /**
     * Update the data of maps once the version changes.
     */
    BaseResult updateMapLib(String version);
}
