package wsg.lol.common;

import java.util.Date;

public class MatchDo {
    private Long gameId;

    private Date gameCreation;

    private Integer gameDuration;

    private String gameVersion;

    private Byte gameMode;

    private Byte gameType;

    private Byte seasonId;

    private Byte queueId;

    private Byte platformId;

    private Byte mapId;

    private Integer frameInterval;

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Date getGameCreation() {
        return gameCreation;
    }

    public void setGameCreation(Date gameCreation) {
        this.gameCreation = gameCreation;
    }

    public Integer getGameDuration() {
        return gameDuration;
    }

    public void setGameDuration(Integer gameDuration) {
        this.gameDuration = gameDuration;
    }

    public String getGameVersion() {
        return gameVersion;
    }

    public void setGameVersion(String gameVersion) {
        this.gameVersion = gameVersion == null ? null : gameVersion.trim();
    }

    public Byte getGameMode() {
        return gameMode;
    }

    public void setGameMode(Byte gameMode) {
        this.gameMode = gameMode;
    }

    public Byte getGameType() {
        return gameType;
    }

    public void setGameType(Byte gameType) {
        this.gameType = gameType;
    }

    public Byte getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Byte seasonId) {
        this.seasonId = seasonId;
    }

    public Byte getQueueId() {
        return queueId;
    }

    public void setQueueId(Byte queueId) {
        this.queueId = queueId;
    }

    public Byte getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Byte platformId) {
        this.platformId = platformId;
    }

    public Byte getMapId() {
        return mapId;
    }

    public void setMapId(Byte mapId) {
        this.mapId = mapId;
    }

    public Integer getFrameInterval() {
        return frameInterval;
    }

    public void setFrameInterval(Integer frameInterval) {
        this.frameInterval = frameInterval;
    }
}