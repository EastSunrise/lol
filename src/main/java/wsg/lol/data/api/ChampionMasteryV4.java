package wsg.lol.data.api;

import org.springframework.stereotype.Component;
import wsg.lol.common.annotation.AccessApi;
import wsg.lol.pojo.dmo.champion.MasteryDmo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author King
 * @date 2019/2/12
 */
@Component
public class ChampionMasteryV4 extends BaseApi {

    /**
     * Get all champion mastery entries sorted by number of champion points descending.
     */
    @AccessApi
    public List<MasteryDmo> getChampionMasteryBySummonerId(String summonerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("encryptedSummonerId", summonerId);
        return getDataArray("/lol/champion-mastery/v4/champion-masteries/by-summoner/{encryptedSummonerId" +
                "}", params, MasteryDmo.class);
    }

    /**
     * Get a champion mastery by player ID and champion ID.
     */
    @AccessApi
    public MasteryDmo getChampionMasteryBySummonerAndChampion(long championId, String summonerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("encryptedSummonerId", summonerId);
        params.put("championId", championId);
        return getDataObject("/lol/champion-mastery/v4/champion-masteries/by-summoner/{encryptedSummonerId" +
                "}/by-champion/{championId}", params, MasteryDmo.class);
    }

    /**
     * Get a player's total champion mastery score, which is the sum of individual champion mastery levels.
     */
    @AccessApi
    public int getScoreBySummonerId(String summonerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("encryptedSummonerId", summonerId);
        return getDataObject("/lol/champion-mastery/v4/scores/by-summoner/{encryptedSummonerId}", params,
                Integer.class);
    }
}
