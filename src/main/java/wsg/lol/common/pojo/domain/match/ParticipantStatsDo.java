package wsg.lol.common.pojo.domain.match;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DO for stats of participants in the match.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "m_participant_stats")
public class ParticipantStatsDo extends BaseDo {

    @Id
    private Long id;

    @Column
    private Boolean win;

    @Column
    private Integer item0;

    @Column
    private Integer item1;

    @Column
    private Integer item2;

    @Column
    private Integer item3;

    @Column
    private Integer item4;

    @Column
    private Integer item5;

    @Column
    private Integer item6;

    @Column
    private Integer kills;

    @Column
    private Integer deaths;

    @Column
    private Integer assists;

    @Column
    private Integer largestKillingSpree;

    @Column
    private Integer largestMultiKill;

    @Column
    private Integer killingSprees;

    @Column
    private Integer longestTimeSpentLiving;

    @Column
    private Integer doubleKills;

    @Column
    private Integer tripleKills;

    @Column
    private Integer quadraKills;

    @Column
    private Integer pentaKills;

    @Column
    private Integer unrealKills;

    @Column
    private Long totalDamageDealt;

    @Column
    private Long magicDamageDealt;

    @Column
    private Long physicalDamageDealt;

    @Column
    private Long trueDamageDealt;

    @Column
    private Integer largestCriticalStrike;

    @Column
    private Long totalDamageDealtToChampions;

    @Column
    private Long magicDamageDealtToChampions;

    @Column
    private Long physicalDamageDealtToChampions;

    @Column
    private Long trueDamageDealtToChampions;

    @Column
    private Long totalHeal;

    @Column
    private Integer totalUnitsHealed;

    @Column
    private Long damageSelfMitigated;

    @Column
    private Long damageDealtToObjectives;

    @Column
    private Long damageDealtToTurrets;

    @Column
    private Long visionScore;

    @Column
    private Integer visionWardsBoughtInGame;

    @Column
    private Integer sightWardsBoughtInGame;

    @Column
    private Integer wardsPlaced;

    @Column
    private Integer wardsKilled;

    @Column
    private Long totalDamageTaken;

    @Column
    private Long magicalDamageTaken;

    @Column
    private Long physicalDamageTaken;

    @Column
    private Long trueDamageTaken;

    @Column
    private Integer goldEarned;

    @Column
    private Integer goldSpent;

    @Column
    private Integer turretKills;

    @Column
    private Integer inhibitorKills;

    @Column
    private Integer totalMinionsKilled;

    @Column
    private Integer neutralMinionsKilled;

    @Column
    private Integer neutralMinionsKilledTeamJungle;

    @Column
    private Integer neutralMinionsKilledEnemyJungle;

    @Column
    private Integer totalTimeCrowdControlDealt;

    @Column
    private Integer champLevel;

    @Column
    private Long timeCCingOthers;

    @Column
    private Boolean firstBloodKill;

    @Column
    private Boolean firstBloodAssist;

    @Column
    private Boolean firstTowerKill;

    @Column
    private Boolean firstTowerAssist;

    @Column
    private Boolean firstInhibitorKill;

    @Column
    private Boolean firstInhibitorAssist;

    @Column
    private Integer combatPlayerScore;

    @Column
    private Integer objectivePlayerScore;

    @Column
    private Integer totalPlayerScore;

    @Column
    private Integer totalScoreRank;

    @Column
    private Integer playerScore0;

    @Column
    private Integer playerScore1;

    @Column
    private Integer playerScore2;

    @Column
    private Integer playerScore3;

    @Column
    private Integer playerScore4;

    @Column
    private Integer playerScore5;

    @Column
    private Integer playerScore6;

    @Column
    private Integer playerScore7;

    @Column
    private Integer playerScore8;

    @Column
    private Integer playerScore9;

    @Column
    private Integer perk0;

    @Column
    private Integer perk0Var1;

    @Column
    private Integer perk0Var2;

    @Column
    private Integer perk0Var3;

    @Column
    private Integer perk1;

    @Column
    private Integer perk1Var1;

    @Column
    private Integer perk1Var2;

    @Column
    private Integer perk1Var3;

    @Column
    private Integer perk2;

    @Column
    private Integer perk2Var1;

    @Column
    private Integer perk2Var2;

    @Column
    private Integer perk2Var3;

    @Column
    private Integer perk3;

    @Column
    private Integer perk3Var1;

    @Column
    private Integer perk3Var2;

    @Column
    private Integer perk3Var3;

    @Column
    private Integer perk4;

    @Column
    private Integer perk4Var1;

    @Column
    private Integer perk4Var2;

    @Column
    private Integer perk4Var3;

    @Column
    private Integer perk5;

    @Column
    private Integer perk5Var1;

    @Column
    private Integer perk5Var2;

    @Column
    private Integer perk5Var3;

    @Column
    private Integer perkPrimaryStyle;

    @Column
    private Integer perkSubStyle;

    @Column
    private Integer statPerk0;

    @Column
    private Integer statPerk1;

    @Column
    private Integer statPerk2;
}