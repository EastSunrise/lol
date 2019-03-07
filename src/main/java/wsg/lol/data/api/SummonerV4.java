package wsg.lol.data.api;

import wsg.lol.dmo.summoner.SummonerDmo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author King
 * @date 2019/2/11
 */
public class SummonerV4 extends BaseApi {

    /**
     * Get a summoner by account ID.
     */
    public static SummonerDmo getSummonersByAccount(String accountId) {
        Map<String, Object> params = new HashMap<>();
        params.put("encryptedAccountId", accountId);
        return getDataObject("/lol/summoner/v4/summoners/by-account/{encryptedAccountId}", params, SummonerDmo.class);
    }

    /**
     * Get a summoner by summoner name.
     */
    public static SummonerDmo getSummonerByName(String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("summonerName", name);
        return getDataObject("/lol/summoner/v4/summoners/by-name/{summonerName}", params, SummonerDmo.class);
    }

    /**
     * Get a summoner by PUUID.
     */
    public static SummonerDmo getSummonersByPuuid(String puuid) {
        Map<String, Object> params = new HashMap<>();
        params.put("encryptedPUUID", puuid);
        return getDataObject("/lol/summoner/v4/summoners/by-puuid/{encryptedPUUID}", params, SummonerDmo.class);
    }

    /**
     * Get a summoner by summoner ID.
     * <p>
     * ATTENTION
     * Consistently looking up summoner ids that don't exist will result in a blacklist.
     */
    public static SummonerDmo getSummonerById(String summonerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("encryptedSummonerId", summonerId);
        return getDataObject("/lol/summoner/v4/summoners/{encryptedSummonerId}", params, SummonerDmo.class);
    }
}
