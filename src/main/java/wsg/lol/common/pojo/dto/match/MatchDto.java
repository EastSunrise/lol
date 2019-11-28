package wsg.lol.common.pojo.dto.match;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.match.GameModeEnum;
import wsg.lol.common.enums.match.GameTypeEnum;
import wsg.lol.common.enums.share.MapEnum;
import wsg.lol.common.enums.share.SeasonEnum;
import wsg.lol.common.enums.system.PlatformRoutingEnum;
import wsg.lol.common.pojo.serialize.CustomEnumDeserializer;

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

    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    private PlatformRoutingEnum platformId;

    private Date gameCreation;

    private Integer gameDuration;

    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    private MapEnum mapId;

    private SeasonEnum seasonId;

    private String gameVersion;

    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    private GameModeEnum gameMode;

    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    private GameTypeEnum gameType;
}
