package wsg.lol.common.pojo.dto.spectator;

import wsg.lol.common.base.BaseDto;
import wsg.lol.common.base.IJson;

import java.util.List;

/**
 * // TODO: (Kingen, 2019/11/18)
 * @author EastSunrise
 */
public class FeaturedGames extends BaseDto implements IJson {

    /**
     * The suggested interval to wait before requesting FeaturedGames again
     */
    private long clientRefreshInterval;

    /**
     * The list of featured games.
     */
    private List<FeaturedGameInfo> gameList;

    public long getClientRefreshInterval() {
        return clientRefreshInterval;
    }

    public void setClientRefreshInterval(long clientRefreshInterval) {
        this.clientRefreshInterval = clientRefreshInterval;
    }

    public List<FeaturedGameInfo> getGameList() {
        return gameList;
    }

    public void setGameList(List<FeaturedGameInfo> gameList) {
        this.gameList = gameList;
    }
}
