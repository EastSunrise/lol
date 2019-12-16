package wsg.lol.common.pojo.dto.match;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.match.TeamIdEnum;

/**
 * DTO for each participant in the match.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ParticipantDto extends BaseDto {

    @JSONField(name = "participantId")
    private Integer participantNum;

    /**
     * 100 for blue side. 200 for red side.
     */
    private TeamIdEnum teamId;

    private Integer championId;

    private Integer spell1Id;

    private Integer spell2Id;

    private ParticipantStatsDto stats;

    private ParticipantTimelineDto timeline;
}
