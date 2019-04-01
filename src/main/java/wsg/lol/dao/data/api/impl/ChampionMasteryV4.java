package wsg.lol.dao.data.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wsg.lol.dao.data.api.base.intf.BaseApi;
import wsg.lol.pojo.annotation.AccessApi;
import wsg.lol.pojo.dto.api.champion.ChampionMasteryDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author King
 */
@Component
public class ChampionMasteryV4 {

    private BaseApi baseApi;

    /**
     * Get all champion mastery entries sorted by number of champion points descending.
     */
    @AccessApi
    public List<ChampionMasteryDto> getChampionMasteryBySummonerId(String summonerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("encryptedSummonerId", summonerId);
        return baseApi.getArray("/lol/champion-mastery/v4/champion-masteries/by-summoner/{encryptedSummonerId" +
                "}", params, ChampionMasteryDto.class);
    }

    /**
     * Get a champion mastery by player ID and champion ID.
     */
    @AccessApi
    public ChampionMasteryDto getChampionMasteryBySummonerAndChampion(long championId, String summonerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("encryptedSummonerId", summonerId);
        params.put("championId", championId);
        return baseApi.getObject("/lol/champion-mastery/v4/champion-masteries/by-summoner/{encryptedSummonerId" +
                "}/by-champion/{championId}", params, ChampionMasteryDto.class);
    }

    /**
     * Get a player's total champion mastery score, which is the sum of individual champion mastery levels.
     */
    @AccessApi
    public int getScoreBySummonerId(String summonerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("encryptedSummonerId", summonerId);
        return baseApi.getObject("/lol/champion-mastery/v4/scores/by-summoner/{encryptedSummonerId}", params,
                Integer.class);
    }

    @Autowired
    public void setBaseApi(BaseApi baseApi) {
        this.baseApi = baseApi;
    }
}
