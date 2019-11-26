package wsg.lol.common.pojo.dto.match;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.game.GameModeEnum;
import wsg.lol.common.enums.game.GameTypeEnum;
import wsg.lol.common.enums.game.MapEnum;
import wsg.lol.common.enums.game.SeasonEnum;
import wsg.lol.common.enums.match.MatchQueueEnum;
import wsg.lol.common.enums.route.PlatformRoutingEnum;
import wsg.lol.common.pojo.parser.CustomEnumDeserializer;
import wsg.lol.common.pojo.parser.IntegerEnumDeserializer;

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

    private Long gameId;
    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    private PlatformRoutingEnum platformId;
    private Date gameCreation;
    private Long gameDuration;
    @JSONField(deserializeUsing = IntegerEnumDeserializer.class)
    private MapEnum mapId;
    private SeasonEnum seasonId;
    @JSONField(deserializeUsing = IntegerEnumDeserializer.class)
    private MatchQueueEnum queueId;
    private String gameVersion;
    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    private GameModeEnum gameMode;
    private GameTypeEnum gameType;

    private List<ParticipantIdentityDto> participantIdentities;
    private List<TeamStatsDto> teams;
    private List<ParticipantDto> participants;
}
