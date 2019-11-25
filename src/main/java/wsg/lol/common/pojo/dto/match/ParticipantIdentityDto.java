package wsg.lol.common.pojo.dto.match;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

/**
 * Bean for reference of players and id.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ParticipantIdentityDto extends BaseDto {

    private PlayerDto player;
    private int participantId;
}
