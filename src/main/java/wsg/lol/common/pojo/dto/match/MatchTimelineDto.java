package wsg.lol.common.pojo.dto.match;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

import java.util.List;

/**
 * Bean for timelines of matches.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MatchTimelineDto extends BaseDto {

    private List<MatchFrameDto> frames;
    private Long frameInterval;
}
