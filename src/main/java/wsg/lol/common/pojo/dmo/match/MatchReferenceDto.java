package wsg.lol.common.pojo.dmo.match;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.game.SeasonEnum;
import wsg.lol.common.enums.match.MatchLaneEnum;
import wsg.lol.common.enums.match.MatchQueueEnum;
import wsg.lol.common.enums.match.MatchRoleEnum;
import wsg.lol.common.enums.route.PlatformEnum;

import java.util.Date;

/**
 * Bean for references of matches.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MatchReferenceDto extends BaseDto {

    private Integer id;
    private MatchLaneEnum lane;
    private Long gameId;
    private Integer champion;
    private PlatformEnum platformId;
    private SeasonEnum season;
    private MatchQueueEnum queue;
    private MatchRoleEnum role;
    private Date gameCreation;
    private String summonerId;
    private Boolean checked;
}
