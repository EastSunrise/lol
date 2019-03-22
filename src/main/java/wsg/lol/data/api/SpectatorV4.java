package wsg.lol.data.api;

import org.springframework.stereotype.Component;
import wsg.lol.common.annotation.AccessApi;
import wsg.lol.pojo.dto.api.spectator.CurrentGameInfo;
import wsg.lol.pojo.dto.api.spectator.FeaturedGames;

import java.util.HashMap;
import java.util.Map;

/**
 * @author King
 * @date 2019/2/12
 */
@Component
public class SpectatorV4 extends BaseApi {

    /**
     * wsg NULL
     * Get current game information for the given summoner ID.
     */
    @AccessApi
    public CurrentGameInfo getActiveGamesBySummoner(String summonerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("encryptedSummonerId", summonerId);
        return getDataObject("/lol/spectator/v4/active-games/by-summoner/{encryptedSummonerId}", params,
                CurrentGameInfo.class);
    }

    /**
     * Get list of featured games. 推荐观战的一组比赛的基本数据
     */
    @AccessApi
    public FeaturedGames getFeaturedGames() {
        return getDataObject("/lol/spectator/v4/featured-games", FeaturedGames.class);
    }
}
