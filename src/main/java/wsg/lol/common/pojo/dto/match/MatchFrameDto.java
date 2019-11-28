package wsg.lol.common.pojo.dto.match;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * DTO for frames of the match.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MatchFrameDto extends BaseDto {

    private Date timestamp;
    private Map<String, MatchParticipantFrameDto> participantFrames;
    private List<MatchEventDto> events;
}
