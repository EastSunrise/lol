package wsg.lol.service.version.intf;

import wsg.lol.common.base.GenericResult;
import wsg.lol.common.base.Result;
import wsg.lol.common.result.version.VersionResult;

/**
 * Service for version control.
 *
 * @author Kingen
 */
public interface VersionService {

    /**
     * Check if the cdn directory exists.
     */
    GenericResult<Boolean> checkCdn(String version);

    /**
     * Get info of version.
     */
    VersionResult getVersion();

    /**
     * Update config of current version.
     */
    Result updateVersion(String version);

    /**
     * Update the data of champions once the version changes.
     */
    Result updateChampions(String version);

    /**
     * Update the data of items once the version changes.
     */
    Result updateItems(String version);

    /**
     * Update the data of reforged runes once the version changes.
     */
    Result updateRunes(String version);

    /**
     * Update the data of maps once the version changes.
     */
    Result updateMaps(String version);

    /**
     * Update the data of profile icons once the version changes.
     */
    Result updateProfileIcons(String version);

    /**
     * Update the data of summoner spells once the version changes.
     */
    Result updateSummonerSpells(String version);
}
