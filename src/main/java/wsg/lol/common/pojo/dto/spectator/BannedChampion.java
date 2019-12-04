package wsg.lol.common.pojo.dto.spectator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.match.TeamIdEnum;

/**
 * DTO for banned champions of the team.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
class BannedChampion extends BaseDto {

    /**
     * The turn during which the champion was banned
     */
    private Integer pickTurn;

    /**
     * The ID of the banned champion
     */
    private Integer championId;

    /**
     * The ID of the team that banned the champion
     */
    private TeamIdEnum teamId;
}
