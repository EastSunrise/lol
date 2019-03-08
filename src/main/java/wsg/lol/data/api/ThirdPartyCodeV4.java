package wsg.lol.data.api;

import wsg.lol.common.constants.annotation.AccessInterval;

import java.util.HashMap;
import java.util.Map;

/**
 * @author King
 * @date 2019/2/11
 */
public class ThirdPartyCodeV4 extends BaseApi {

    /**
     * Get third party code for a given summoner ID.
     * <p>
     * wsg Valid codes must be no longer than 256 characters and only use valid characters: 0-9, a-z, A-Z, and -
     */
    @AccessInterval
    public static String getThirdPartyCodeBySummoner(String summonerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("encryptedSummonerId", summonerId);
        return getDataObject("/lol/platform/v4/third-party-code/by-summoner/{encryptedSummonerId}", params,
                String.class);
    }
}
