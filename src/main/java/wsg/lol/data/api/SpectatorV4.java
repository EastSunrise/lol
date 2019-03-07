package wsg.lol.data.api;

import wsg.lol.dto.api.spectator.CurrentGameInfo;
import wsg.lol.dto.api.spectator.FeaturedGames;

import java.util.HashMap;
import java.util.Map;

/**
 * @author King
 * @date 2019/2/12
 */
public class SpectatorV4 extends BaseApi {

    /**
     * wsg NULL
     * Get current game information for the given summoner ID.
     */
    public static CurrentGameInfo getActiveGamesBySummoner(String summonerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("encryptedSummonerId", summonerId);
        return getDataObject("/lol/spectator/v4/active-games/by-summoner/{encryptedSummonerId}", params,
                CurrentGameInfo.class);
    }

    /**
     * Get list of featured games. 推荐观战的一组比赛的基本数据
     */
    public static FeaturedGames getFeaturedGames() {
        return getDataObject("/lol/spectator/v4/featured-games", FeaturedGames.class);
    }
}
