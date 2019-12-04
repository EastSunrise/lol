package wsg.lol.common.pojo.dto.match;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.match.GameModeEnum;
import wsg.lol.common.enums.match.GameTypeEnum;
import wsg.lol.common.enums.match.MatchQueueEnum;
import wsg.lol.common.enums.share.MapEnum;
import wsg.lol.common.enums.share.SeasonEnum;
import wsg.lol.common.enums.system.PlatformRoutingEnum;

import java.util.Date;

/**
 * DTO for a match.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MatchDto extends BaseDto {

    private Long gameId;

    private PlatformRoutingEnum platformId;

    private Date gameCreation;

    private Integer gameDuration;

    private MapEnum mapId;

    private SeasonEnum seasonId;

    private String gameVersion;

    private GameModeEnum gameMode;

    private GameTypeEnum gameType;

    private MatchQueueEnum queueId;
}
