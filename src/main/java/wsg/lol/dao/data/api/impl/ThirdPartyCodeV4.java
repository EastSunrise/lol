package wsg.lol.dao.data.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import wsg.lol.dao.data.api.base.intf.BaseApi;
import wsg.lol.pojo.annotation.AccessApi;

import java.util.HashMap;
import java.util.Map;

/**
 * @author King
 */
public class ThirdPartyCodeV4 {

    private BaseApi baseApi;

    /**
     * Get third party code for a given summoner ID.
     * <p>
     * wsg Valid codes must be no longer than 256 characters and only use valid characters: 0-9, a-z, A-Z, and -
     */
    @AccessApi
    public String getThirdPartyCodeBySummoner(String summonerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("encryptedSummonerId", summonerId);
        return baseApi.getObject("/lol/platform/v4/third-party-code/by-summoner/{encryptedSummonerId}", params,
                String.class);
    }

    @Autowired
    public void setBaseApi(BaseApi baseApi) {
        this.baseApi = baseApi;
    }
}
