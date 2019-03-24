package wsg.lol.pojo.dmo.match;


import com.alibaba.fastjson.annotation.JSONField;
import wsg.lol.common.serializer.IntEnumSerializer;
import wsg.lol.pojo.base.BaseDmo;
import wsg.lol.pojo.enums.impl.code.*;

import java.util.Date;

public class MatchDmo extends BaseDmo {

    private Long gameId;
    private Date gameCreation;
    private Integer gameDuration;
    private String gameVersion;
    private GameModeEnum gameMode;
    private GameTypeEnum gameType;

    @JSONField(deserializeUsing = IntEnumSerializer.class, serializeUsing = IntEnumSerializer.class)
    private SeasonEnum seasonId;

    @JSONField(deserializeUsing = IntEnumSerializer.class, serializeUsing = IntEnumSerializer.class)
    private MatchQueueEnum queueId;

    private PlatformEnum platformId;

    @JSONField(deserializeUsing = IntEnumSerializer.class, serializeUsing = IntEnumSerializer.class)
    private MapEnum mapId;

    /**
     * wsg Team
     */
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
        this.gameVersion = gameVersion;
    }

    public GameModeEnum getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameModeEnum gameMode) {
        this.gameMode = gameMode;
    }

    public GameTypeEnum getGameType() {
        return gameType;
    }

    public void setGameType(GameTypeEnum gameType) {
        this.gameType = gameType;
    }

    public SeasonEnum getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(SeasonEnum seasonId) {
        this.seasonId = seasonId;
    }

    public MatchQueueEnum getQueueId() {
        return queueId;
    }

    public void setQueueId(MatchQueueEnum queueId) {
        this.queueId = queueId;
    }

    public PlatformEnum getPlatformId() {
        return platformId;
    }

    public void setPlatformId(PlatformEnum platformId) {
        this.platformId = platformId;
    }

    public MapEnum getMapId() {
        return mapId;
    }

    public void setMapId(MapEnum mapId) {
        this.mapId = mapId;
    }

    public Integer getFrameInterval() {
        return frameInterval;
    }

    public void setFrameInterval(Integer frameInterval) {
        this.frameInterval = frameInterval;
    }

}
