package wsg.lol.dao.api.impl;

import org.springframework.stereotype.Component;
import wsg.lol.common.pojo.dto.spectator.CurrentGameInfo;
import wsg.lol.common.pojo.dto.spectator.FeaturedGames;
import wsg.lol.dao.api.client.BaseApi;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kingen
 * @see <a href="https://developer.riotgames.com/apis#spectator-v4">SPECTATOR-V4</a>
 */
@Component
public class SpectatorV4 extends BaseApi {

    /**
     * Get current game information for the given summoner ID.
     *
     * @see <a href="https://developer.riotgames.com/apis#spectator-v4/GET_getCurrentGameInfoBySummoner/>
     */
    public CurrentGameInfo getActiveGamesBySummoner(String summonerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("encryptedSummonerId", summonerId);
        return this.getObject("/lol/spectator/v4/active-games/by-summoner/{encryptedSummonerId}", params, CurrentGameInfo.class);
    }

    /**
     * Get list of featured games. 推荐观战的一组比赛的基本数据
     *
     * @see <a href="https://developer.riotgames.com/apis#spectator-v4/GET_getFeaturedGames"/>
     */
    public FeaturedGames getFeaturedGames() {
        return this.getObject("/lol/spectator/v4/featured-games", FeaturedGames.class);
    }
}
