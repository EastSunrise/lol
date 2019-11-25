package wsg.lol.common.pojo.dto.match;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

/**
 * Bean for reference of bans of the team.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TeamBansDto extends BaseDto {
    /**
     * Turn during which the champion was banned.
     */
    private Integer pickTurn;

    /**
     * Banned championId.
     */
    private Integer championId;
}
