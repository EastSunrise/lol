package wsg.lol.common.pojo.dto.match;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.match.MatchLaneEnum;
import wsg.lol.common.enums.match.MatchQueueEnum;
import wsg.lol.common.enums.match.MatchRoleEnum;
import wsg.lol.common.enums.share.SeasonEnum;
import wsg.lol.common.enums.system.PlatformRoutingEnum;
import wsg.lol.common.pojo.serialize.CustomEnumDeserializer;

import java.util.Date;

/**
 * DTO for each reference of the matches.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MatchReferenceDto extends BaseDto {

    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    private MatchLaneEnum lane;
    private Long gameId;
    private Integer champion;
    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    private PlatformRoutingEnum platformId;
    private SeasonEnum season;
    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    private MatchQueueEnum queue;
    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    private MatchRoleEnum role;
    private Date timestamp;
}
