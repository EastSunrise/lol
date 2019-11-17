package wsg.lol.service.version.intf;

import wsg.lol.common.result.base.GenericResult;
import wsg.lol.common.result.base.Result;
import wsg.lol.common.result.version.VersionResult;

/**
 * 版本更新
 *
 * @author EastSunrise
 */
public interface VersionService {

    /**
     * 数据目录是否存在
     */
    GenericResult<Boolean> isCdnExists(String version);

    /**
     * get info of version.
     */
    VersionResult getVersion();

    /**
     * Update the static data to the certain version.
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
     * Update the data of summoner spells once the version changes.
     */
    Result updateSummonerSpellLib(String version);

    /**
     * Update the data of maps once the version changes.
     */
    Result updateMaps(String version);
}
