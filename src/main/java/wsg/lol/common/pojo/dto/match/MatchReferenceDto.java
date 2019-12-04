package wsg.lol.common.pojo.dto.match;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.match.MatchLaneEnum;
import wsg.lol.common.enums.match.MatchQueueEnum;
import wsg.lol.common.enums.match.MatchRoleEnum;
import wsg.lol.common.enums.share.SeasonEnum;
import wsg.lol.common.enums.system.PlatformRoutingEnum;

import java.util.Date;

/**
 * DTO for each reference of the matches.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MatchReferenceDto extends BaseDto {

    private MatchLaneEnum lane;

    private Long gameId;

    private Integer champion;

    private PlatformRoutingEnum platformId;

    private SeasonEnum season;

    private MatchQueueEnum queue;

    private MatchRoleEnum role;

    private Date timestamp;
}
