package wsg.lol.common.pojo.dto.match;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.match.TeamIdEnum;
import wsg.lol.common.enums.match.TeamResultEnum;

import java.util.List;

/**
 * DTO for stats of a team.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TeamStatsDto extends BaseDto {

    /**
     * 100 for blue side. 200 for red side.
     */
    private TeamIdEnum teamId;

    /**
     * String indicating whether or not the team won. There are only two values visibile in public match history.
     * (Legal
     * values: Fail, Win)
     */
    private TeamResultEnum win;

    /**
     * Flag indicating whether or not the team scored the first blood.
     */
    private Boolean firstBlood;

    /**
     * Flag indicating whether or not the team destroyed the first tower.
     */
    private Boolean firstTower;

    /**
     * Flag indicating whether or not the team destroyed the first inhibitor.
     */
    private Boolean firstInhibitor;

    /**
     * Flag indicating whether or not the team scored the first Baron kill.
     */
    private Boolean firstBaron;

    /**
     * Flag indicating whether or not the team scored the first Dragon kill.
     */
    private Boolean firstDragon;

    /**
     * Flag indicating whether or not the team scored the first Rift Herald kill.
     */
    private Boolean firstRiftHerald;

    /**
     * Number of towers the team destroyed.
     */
    private Integer towerKills;

    /**
     * Number of inhibitors the team destroyed.
     */
    private Integer inhibitorKills;

    /**
     * Number of times the team killed Baron.
     */
    private Integer baronKills;

    /**
     * Number of times the team killed Dragon.
     */
    private Integer dragonKills;

    /**
     * Number of times the team killed Vilemaw.
     */
    private Integer vilemawKills;

    /**
     * Number of times the team killed Rift Herald.
     */
    private Integer riftHeraldKills;

    /**
     * For Dominion match, specifies the points the team had at game end.
     */
    private Integer dominionVictoryScore;

    /**
     * If match queueId has a draft, contains banned champion data, otherwise empty.
     */
    private List<TeamBansDto> bans;

    @Data
    public static class TeamBansDto {
        private Integer pickTurn;
        private Integer championId;
    }
}
