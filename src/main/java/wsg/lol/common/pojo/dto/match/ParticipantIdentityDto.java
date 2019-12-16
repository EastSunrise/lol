package wsg.lol.common.pojo.dto.match;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

/**
 * DTO for reference of players and id.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ParticipantIdentityDto extends BaseDto {

    private PlayerDto player;

    /**
     * From 1 to 10.
     */
    @JSONField(name = "participantId")
    private int participantNum;
}
