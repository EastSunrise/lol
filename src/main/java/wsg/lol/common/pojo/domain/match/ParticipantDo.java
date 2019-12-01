package wsg.lol.common.pojo.domain.match;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDo;
import wsg.lol.common.enums.match.MatchLaneEnum;
import wsg.lol.common.enums.match.MatchRoleEnum;
import wsg.lol.common.enums.match.TeamIdEnum;
import wsg.lol.common.enums.system.PlatformRoutingEnum;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DTO for each participant in the match.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "m_participant")
public class ParticipantDo extends BaseDo {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column
    private Long gameId;

    @Column
    private Integer participantId;

    @Column
    private PlatformRoutingEnum platformId;

    @Column
    private String summonerId;

    @Column
    private String accountId;

    @Column
    private PlatformRoutingEnum currentPlatformId;

    @Column
    private String currentAccountId;

    @Column
    private String matchHistoryUri;

    @Column
    private Integer profileIcon;

    @Column
    private TeamIdEnum teamId;

    @Column
    private Integer championId;

    @Column
    private Integer spell1Id;

    @Column
    private Integer spell2Id;

    @Column
    private MatchRoleEnum role;

    @Column
    private MatchLaneEnum lane;
}