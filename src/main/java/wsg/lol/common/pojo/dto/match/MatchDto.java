package wsg.lol.common.pojo.dto.match;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.game.*;
import wsg.lol.common.enums.route.PlatformRoutingEnum;

import java.util.Date;
import java.util.List;

/**
 * Bean for a match.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MatchDto extends BaseDto {

    private SeasonEnum seasonId;
    private QueueIdEnum queueId;
    private Long gameId;
    private String gameVersion;
    private PlatformRoutingEnum platformId;
    private GameModeEnum gameMode;
    private MapEnum mapId;
    private GameTypeEnum gameType;
    private Long gameDuration;
    private Date gameCreation;
    private List<ParticipantIdentityDto> participantIdentities;
    private List<TeamStatsDto> teams;
    private List<ParticipantDto> participants;
}
