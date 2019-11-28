package wsg.lol.common.pojo.domain.match;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDo;
import wsg.lol.common.enums.match.TeamIdEnum;
import wsg.lol.common.enums.match.TeamResultEnum;
import wsg.lol.common.pojo.dto.match.TeamStatsDto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * DO for stats of the team in the match.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(schema = "lol", name = "m_team_stats")
public class TeamStatsDo extends BaseDo {

    @Id
    private Long gameId;

    @Id
    private TeamIdEnum teamId;

    @Column
    private TeamResultEnum win;

    @Column
    private Boolean firstBlood;

    @Column
    private Boolean firstTower;

    @Column
    private Boolean firstInhibitor;

    @Column
    private Boolean firstBaron;

    @Column
    private Boolean firstDragon;

    @Column
    private Boolean firstRiftHerald;

    @Column
    private Integer towerKills;

    @Column
    private Integer inhibitorKills;

    @Column
    private Integer baronKills;

    @Column
    private Integer dragonKills;

    @Column
    private Integer vilemawKills;

    @Column
    private Integer riftHeraldKills;

    @Column
    private Integer dominionVictoryScore;

    @Column
    private List<TeamStatsDto.TeamBansDto> bans;
}