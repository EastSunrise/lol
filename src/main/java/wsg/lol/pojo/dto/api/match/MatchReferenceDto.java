package wsg.lol.pojo.dto.api.match;

import wsg.lol.pojo.enums.impl.name.MatchLaneEnum;

/**
 * @author King
 * @date 2019/2/12
 */
public class MatchReferenceDto {

    private MatchLaneEnum lane;
    private long gameId;
    private int champion;
    private String platformId;
    private int season;
    private int queue;
    private String role;
    private long timestamp;

    public MatchLaneEnum getLane() {
        return lane;
    }

    public void setLane(MatchLaneEnum lane) {
        this.lane = lane;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public int getChampion() {
        return champion;
    }

    public void setChampion(int champion) {
        this.champion = champion;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getQueue() {
        return queue;
    }

    public void setQueue(int queue) {
        this.queue = queue;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
