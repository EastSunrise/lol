package wsg.lol.common.pojo.dto.match;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.match.TeamIdEnum;
import wsg.lol.common.pojo.parser.IntegerEnumDeserializer;

/**
 * Bean for each participant in the match.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ParticipantDto extends BaseDto {

    private Integer participantId;

    /**
     * 100 for blue side. 200 for red side.
     */
    @JSONField(deserializeUsing = IntegerEnumDeserializer.class)
    private TeamIdEnum teamId;

    private Integer championId;

    private Integer spell1Id;

    private Integer spell2Id;

    private ParticipantStatsDto stats;

    private ParticipantTimelineDto timeline;
}
