package wsg.lol.pojo.dto.api.spectator;

import java.util.List;

/**
 * @author King
 */
public class FeaturedGames {

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
