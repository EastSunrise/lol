package wsg.lol.common.pojo.dto.match;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

/**
 * DTO for timelines of each participant in the match.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MatchParticipantFrameDto extends BaseDto {

    @JSONField(name = "participantId")
    private Integer participantNum;
    private MatchPositionDto position;

    private Integer currentGold;
    private Integer totalGold;
    private Integer level;
    private Integer xp;
    private Integer minionsKilled;
    private Integer jungleMinionsKilled;
    private Integer dominionScore;
    private Integer teamScore;
}
