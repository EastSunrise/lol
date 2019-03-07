package wsg.lol.dto.api.tournament;

import wsg.lol.common.base.QueryDto;
import wsg.lol.common.enums.impl.id.MapEnum;
import wsg.lol.common.enums.impl.others.PickTypeEnum;
import wsg.lol.common.enums.impl.others.SpectatorTypeEnum;

import java.util.Set;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-01 13:37
 */
public class PostTournamentCodeDto extends QueryDto {

    /**
     * The spectator type of the game.
     */
    private SpectatorTypeEnum spectatorType;

    /**
     * The team size of the game. Valid values are 1-5.
     */
    private Integer teamSize;

    /**
     * The pick type of the game.
     */
    private PickTypeEnum pickType;

    /**
     * Optional list of encrypted summonerIds in order to validate the players eligible
     * to join the lobby.
     * <p>
     * NOTE: We currently do not enforce participants at the team level, but rather the aggregate of teamOne and
     * teamTwo. We may add the ability to enforce at the team level in the future.
     */
    private Set<String> allowedSummonerIds;

    /**
     * The map type of the game.
     */
    private MapEnum mapType;

    /**
     * Optional string that may contain any data in any format, if specified at all.
     * Used to denote any custom information about the game.
     */
    private String metadata;

    public SpectatorTypeEnum getSpectatorType() {
        return spectatorType;
    }

    public void setSpectatorType(SpectatorTypeEnum spectatorType) {
        this.spectatorType = spectatorType;
    }

    public Integer getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(Integer teamSize) {
        this.teamSize = teamSize;
    }

    public PickTypeEnum getPickType() {
        return pickType;
    }

    public void setPickType(PickTypeEnum pickType) {
        this.pickType = pickType;
    }

    public Set<String> getAllowedSummonerIds() {
        return allowedSummonerIds;
    }

    public void setAllowedSummonerIds(Set<String> allowedSummonerIds) {
        this.allowedSummonerIds = allowedSummonerIds;
    }

    public MapEnum getMapType() {
        return mapType;
    }

    public void setMapType(MapEnum mapType) {
        this.mapType = mapType;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }
}
