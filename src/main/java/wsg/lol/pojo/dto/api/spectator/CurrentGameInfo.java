package wsg.lol.pojo.dto.api.spectator;

import wsg.lol.pojo.base.BaseDto;
import wsg.lol.pojo.base.IJson;

import java.util.List;

/**
 * @author King
 */
public class CurrentGameInfo extends BaseDto implements IJson {

    /**
     * The ID of the game
     */
    private long gameId;

    /**
     * The game start time represented in epoch milliseconds
     */
    private long gameStartTime;

    /**
     * The ID of the platform on which the game is being played
     */
    private String platformId;

    /**
     * The game mode
     */
    private String gameMode;

    /**
     * The ID of the map
     */
    private long mapId;

    /**
     * The game type
     */
    private String gameType;

    /**
     * Banned champion information
     */
    private List<BannedChampion> bannedChampions;

    /**
     * The observer information
     */
    private Observer observers;

    /**
     * The participant information
     */
    private List<CurrentGameParticipant> participants;

    /**
     * The amount of time in seconds that has passed since the game started
     */
    private long gameLength;

    /**
     * The queue type (queue types are documented on the Game Constants page)
     */
    private long gameQueueConfigId;

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public long getGameStartTime() {
        return gameStartTime;
    }

    public void setGameStartTime(long gameStartTime) {
        this.gameStartTime = gameStartTime;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public long getMapId() {
        return mapId;
    }

    public void setMapId(long mapId) {
        this.mapId = mapId;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public List<BannedChampion> getBannedChampions() {
        return bannedChampions;
    }

    public void setBannedChampions(List<BannedChampion> bannedChampions) {
        this.bannedChampions = bannedChampions;
    }

    public Observer getObservers() {
        return observers;
    }

    public void setObservers(Observer observers) {
        this.observers = observers;
    }

    public List<CurrentGameParticipant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<CurrentGameParticipant> participants) {
        this.participants = participants;
    }

    public long getGameLength() {
        return gameLength;
    }

    public void setGameLength(long gameLength) {
        this.gameLength = gameLength;
    }

    public long getGameQueueConfigId() {
        return gameQueueConfigId;
    }

    public void setGameQueueConfigId(long gameQueueConfigId) {
        this.gameQueueConfigId = gameQueueConfigId;
    }
}
