package wsg.lol.dao.api.impl;

import org.springframework.stereotype.Component;
import wsg.lol.common.pojo.dto.summoner.SummonerDto;
import wsg.lol.dao.api.client.BaseApi;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kingen
 * @see <a href="https://developer.riotgames.com/apis#summoner-v4">SUMMONER-V4</a>
 */
@Component
public class SummonerV4 extends BaseApi {

    /**
     * Get a summoner by summoner ID, name, puuid or accountId.
     * <p>
     * ATTENTION Consistently looking up summoner ids that don't exist will result in a blacklist.
     *
     * @see <a href="https://developer.riotgames.com/apis#summoner-v4/GET_getByAccountId"/>
     * @see <a href="https://developer.riotgames.com/apis#summoner-v4/GET_getBySummonerName"/>
     * @see <a href="https://developer.riotgames.com/apis#summoner-v4/GET_getByPUUID"/>
     * @see <a href="https://developer.riotgames.com/apis#summoner-v4/GET_getBySummonerId"/>
     */
    public SummonerDto getSummoner(CondKeyEnum condKeyEnum, String value) {
        Map<String, Object> params = new HashMap<>();
        params.put(condKeyEnum.key, value);
        return this.getObject("/lol/summoner/v4/summoners/" + condKeyEnum.url, params, SummonerDto.class);
    }

    public enum CondKeyEnum {
        ID("encryptedSummonerId", "{encryptedSummonerId}"),
        ACCOUNTID("encryptedAccountId", "by-account/{encryptedAccountId}"),
        NAME("summonerName", "by-name/{summonerName}"),
        PUUID("encryptedPUUID", "by-puuid/{encryptedPUUID}");
        private String key;
        private String url;

        CondKeyEnum(String key, String url) {
            this.key = key;
            this.url = url;
        }
    }
}
