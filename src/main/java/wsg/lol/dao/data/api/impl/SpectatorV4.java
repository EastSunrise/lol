package wsg.lol.dao.data.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wsg.lol.dao.data.api.base.intf.BaseApi;
import wsg.lol.pojo.annotation.AccessApi;
import wsg.lol.pojo.dto.api.spectator.CurrentGameInfo;
import wsg.lol.pojo.dto.api.spectator.FeaturedGames;

import java.util.HashMap;
import java.util.Map;

/**
 * @author King
 */
@Component
public class SpectatorV4 {

    private BaseApi baseApi;

    /**
     * wsg NULL
     * Get current game information for the given summoner ID.
     */
    @AccessApi
    public CurrentGameInfo getActiveGamesBySummoner(String summonerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("encryptedSummonerId", summonerId);
        return baseApi.getObject("/lol/spectator/v4/active-games/by-summoner/{encryptedSummonerId}", params,
                CurrentGameInfo.class);
    }

    /**
     * Get list of featured games. 推荐观战的一组比赛的基本数据
     */
    @AccessApi
    public FeaturedGames getFeaturedGames() {
        return baseApi.getObject("/lol/spectator/v4/featured-games", FeaturedGames.class);
    }

    @Autowired
    public void setBaseApi(BaseApi baseApi) {
        this.baseApi = baseApi;
    }
}
