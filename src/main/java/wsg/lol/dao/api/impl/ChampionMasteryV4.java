package wsg.lol.dao.api.impl;

import org.springframework.stereotype.Component;
import wsg.lol.common.pojo.dto.summoner.ChampionMasteryDto;
import wsg.lol.dao.api.client.BaseApi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kingen
 * @see <a href="https://developer.riotgames.com/apis#champion-mastery-v4">CHAMPION-MASTERY-V4</a>
 */
@Component
public class ChampionMasteryV4 extends BaseApi {

    /**
     * Get all champion mastery entries sorted by number of champion points descending.
     *
     * @see <a href="https://developer.riotgames.com/apis#champion-mastery-v4/GET_getAllChampionMasteries"/>
     */
    public List<ChampionMasteryDto> getChampionMasteryBySummonerId(String summonerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("encryptedSummonerId", summonerId);
        return this.getArray("/lol/champion-mastery/v4/champion-masteries/by-summoner/{encryptedSummonerId}", params, ChampionMasteryDto.class);
    }

    /**
     * Get a champion mastery by player ID and champion ID.
     *
     * @see <a href="https://developer.riotgames.com/apis#champion-mastery-v4/GET_getChampionMastery"/>
     */
    public ChampionMasteryDto getChampionMasteryBySummonerAndChampion(long championId, String summonerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("encryptedSummonerId", summonerId);
        params.put("championId", championId);
        return this.getObject("/lol/champion-mastery/v4/champion-masteries/by-summoner/{encryptedSummonerId}/by-champion/{championId}", params, ChampionMasteryDto.class);
    }

    /**
     * Get a player's total champion mastery score, which is the sum of individual champion mastery levels.
     *
     * @see <a href="https://developer.riotgames.com/apis#champion-mastery-v4/GET_getChampionMasteryScore"/>
     */
    public int getScoreBySummonerId(String summonerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("encryptedSummonerId", summonerId);
        return this.getObject("/lol/champion-mastery/v4/scores/by-summoner/{encryptedSummonerId}", params, Integer.class);
    }
}
