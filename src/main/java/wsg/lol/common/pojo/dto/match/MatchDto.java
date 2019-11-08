package wsg.lol.common.pojo.dto.match;

import org.springframework.data.annotation.Id;
import wsg.lol.common.pojo.base.BaseDto;
import wsg.lol.common.pojo.base.IJson;

import java.util.List;

/**
 * wsg
 *
 * @author EastSunrise
 */
public class MatchDto extends BaseDto implements IJson {

    private int seasonId;
    private int queueId;

    @Id
    private long gameId;
    private String gameVersion;
    private String platformId;
    private String gameMode;
    private int mapId;
    private String gameType;
    private int gameDuration;
    private long gameCreation;
    private List<ParticipantIdentityDto> participantIdentities;
    private List<TeamStatsDto> teams;
    private List<ParticipantDto> participants;

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public int getQueueId() {
        return queueId;
    }

    public void setQueueId(int queueId) {
        this.queueId = queueId;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public String getGameVersion() {
        return gameVersion;
    }

    public void setGameVersion(String gameVersion) {
        this.gameVersion = gameVersion;
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

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public int getGameDuration() {
        return gameDuration;
    }

    public void setGameDuration(int gameDuration) {
        this.gameDuration = gameDuration;
    }

    public long getGameCreation() {
        return gameCreation;
    }

    public void setGameCreation(long gameCreation) {
        this.gameCreation = gameCreation;
    }

    public List<ParticipantIdentityDto> getParticipantIdentities() {
        return participantIdentities;
    }

    public void setParticipantIdentities(List<ParticipantIdentityDto> participantIdentities) {
        this.participantIdentities = participantIdentities;
    }

    public List<TeamStatsDto> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamStatsDto> teams) {
        this.teams = teams;
    }

    public List<ParticipantDto> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ParticipantDto> participants) {
        this.participants = participants;
    }

}
