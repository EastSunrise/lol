package wsg.lol.common.pojo.dto.match;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.pojo.serialize.DurationDeserializer;

import java.time.Duration;
import java.util.List;

/**
 * DTO for timelines of matches.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MatchTimelineDto extends BaseDto {

    private List<MatchFrameDto> frames;

    @JSONField(deserializeUsing = DurationDeserializer.class)
    private Duration frameInterval;
}
