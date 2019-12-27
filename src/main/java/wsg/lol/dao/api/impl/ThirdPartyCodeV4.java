package wsg.lol.dao.api.impl;

import org.springframework.stereotype.Component;
import wsg.lol.dao.api.client.BaseApi;
import wsg.lol.service.aop.Performance;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kingen
 * @see <a href="https://developer.riotgames.com/apis#third-party-code-v4">THIRD-PARTY-CODE-V4</a>
 */
@Component
@Performance
public class ThirdPartyCodeV4 extends BaseApi {

    /**
     * Get third party code for a given summoner ID.
     * <p>
     * Valid codes must be no longer than 256 characters and only use valid characters: 0-9, a-z, A-Z, and -
     *
     * @see <a href="https://developer.riotgames.com/apis#third-party-code-v4/GET_getThirdPartyCodeBySummonerId"/>
     */
    public String getThirdPartyCodeBySummoner(String summonerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("encryptedSummonerId", summonerId);
        return this.getObject("/lol/platform/v4/third-party-code/by-summoner/{encryptedSummonerId}", params, String.class);
    }
}
