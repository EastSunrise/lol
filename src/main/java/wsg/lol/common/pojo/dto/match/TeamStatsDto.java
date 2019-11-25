package wsg.lol.common.pojo.dto.match;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.match.TeamIdEnum;
import wsg.lol.common.enums.match.TeamResultEnum;

import java.util.List;

/**
 * Bean for stats of a team.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TeamStatsDto extends BaseDto {

    /**
     * Flag indicating whether or not the team scored the first Dragon kill.
     */
    private Boolean firstDragon;

    /**
     * Flag indicating whether or not the team destroyed the first inhibitor.
     */
    private Boolean firstInhibitor;

    /**
     * If match queueId has a draft, contains banned champion data, otherwise empty.
     */
    private List<TeamBansDto> bans;

    /**
     * Number of times the team killed Baron.
     */
    private Integer baronKills;

    /**
     * Flag indicating whether or not the team scored the first Rift Herald kill.
     */
    private Boolean firstRiftHerald;

    /**
     * Flag indicating whether or not the team scored the first Baron kill.
     */
    private Boolean firstBaron;

    /**
     * Number of times the team killed Rift Herald.
     */
    private Integer riftHeraldKills;

    /**
     * Flag indicating whether or not the team scored the first blood.
     */
    private Boolean firstBlood;

    /**
     * 100 for blue side. 200 for red side.
     */
    private TeamIdEnum teamId;

    /**
     * Flag indicating whether or not the team destroyed the first tower.
     */
    private Boolean firstTower;

    /**
     * Number of times the team killed Vilemaw.
     */
    private Integer vilemawKills;

    /**
     * Number of inhibitors the team destroyed.
     */
    private Integer inhibitorKills;

    /**
     * Number of towers the team destroyed.
     */
    private Integer towerKills;

    /**
     * For Dominion match, specifies the points the team had at game end.
     */
    private Integer dominionVictoryScore;

    /**
     * String indicating whether or not the team won. There are only two values visibile in public match history.
     * (Legal
     * values: Fail, Win)
     */
    private TeamResultEnum win;

    /**
     * Number of times the team killed Dragon.
     */
    private Integer dragonKills;
}
