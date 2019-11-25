package wsg.lol.common.pojo.dto.match;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

/**
 * Bean for stats of participants in the match.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ParticipantStatsDto extends BaseDto {

    private Boolean firstBloodAssist;
    private Long visionScore;
    private Long magicDamageDealtToChampions;
    private Long damageDealtToObjectives;
    private Integer totalTimeCrowdControlDealt;
    private Integer longestTimeSpentLiving;

    /**
     * Post game rune stats.
     */
    private Integer perk1Var1;

    /**
     * Post game rune stats.
     */
    private Integer perk1Var3;

    /**
     * Post game rune stats.
     */
    private Integer perk1Var2;
    private Integer tripleKills;

    /**
     * Post game rune stats.
     */
    private Integer perk3Var3;
    private Integer nodeNeutralizeAssist;

    /**
     * Post game rune stats.
     */
    private Integer perk3Var2;
    private Integer playerScore9;
    private Integer playerScore8;
    private Integer kills;
    private Integer playerScore1;
    private Integer playerScore0;
    private Integer playerScore3;
    private Integer playerScore2;
    private Integer playerScore5;
    private Integer playerScore4;
    private Integer playerScore7;
    private Integer playerScore6;

    /**
     * Post game rune stats.
     */
    private Integer perk5Var1;

    /**
     * Post game rune stats.
     */
    private Integer perk5Var3;

    /**
     * Post game rune stats.
     */
    private Integer perk5Var2;
    private Integer totalScoreRank;
    private Integer neutralMinionsKilled;
    private Long damageDealtToTurrets;
    private Long physicalDamageDealtToChampions;
    private Integer nodeCapture;
    private Integer largestMultiKill;

    /**
     * Post game rune stats.
     */
    private Integer perk2Var2;

    /**
     * Post game rune stats.
     */
    private Integer perk2Var3;
    private Integer totalUnitsHealed;

    /**
     * Post game rune stats.
     */
    private Integer perk2Var1;

    /**
     * Post game rune stats.
     */
    private Integer perk4Var1;

    /**
     * Post game rune stats.
     */
    private Integer perk4Var2;

    /**
     * Post game rune stats.
     */
    private Integer perk4Var3;
    private Integer wardsKilled;
    private Integer largestCriticalStrike;
    private Integer largestKillingSpree;
    private Integer quadraKills;
    private Integer teamObjective;
    private Long magicDamageDealt;
    private Integer item2;
    private Integer item3;
    private Integer item0;
    private Integer neutralMinionsKilledTeamJungle;
    private Integer item6;
    private Integer item4;
    private Integer item5;

    /**
     * Primary path rune.
     */
    private Integer perk1;

    /**
     * Primary path keystone rune.
     */
    private Integer perk0;

    /**
     * Primary path rune.
     */
    private Integer perk3;

    /**
     * Primary path rune.
     */
    private Integer perk2;

    /**
     * Secondary path rune.
     */
    private Integer perk5;

    /**
     * Secondary path rune.
     */
    private Integer perk4;

    /**
     * Post game rune stats.
     */
    private Integer perk3Var1;
    private Long damageSelfMitigated;
    private Long magicalDamageTaken;
    private Boolean firstInhibitorKill;
    private Long trueDamageTaken;
    private Integer nodeNeutralize;
    private Integer assists;
    private Integer combatPlayerScore;

    /**
     * Primary rune path
     */
    private Integer perkPrimaryStyle;
    private Integer goldSpent;
    private Long trueDamageDealt;
    private Integer participantId;
    private Long totalDamageTaken;
    private Long physicalDamageDealt;
    private Integer sightWardsBoughtInGame;
    private Long totalDamageDealtToChampions;
    private Long physicalDamageTaken;
    private Integer totalPlayerScore;
    private Boolean win;
    private Integer objectivePlayerScore;
    private Long totalDamageDealt;
    private Integer item1;
    private Integer neutralMinionsKilledEnemyJungle;
    private Integer deaths;
    private Integer wardsPlaced;

    /**
     * Secondary rune path
     */
    private Integer perkSubStyle;
    private Integer turretKills;
    private Boolean firstBloodKill;
    private Long trueDamageDealtToChampions;
    private Integer goldEarned;
    private Integer killingSprees;
    private Integer unrealKills;
    private Integer altarsCaptured;
    private Boolean firstTowerAssist;
    private Boolean firstTowerKill;
    private Integer champLevel;
    private Integer doubleKills;
    private Integer nodeCaptureAssist;
    private Integer inhibitorKills;
    private Boolean firstInhibitorAssist;

    /**
     * Post game rune stats.
     */
    private Integer perk0Var1;

    /**
     * Post game rune stats.
     */
    private Integer perk0Var2;

    /**
     * Post game rune stats.
     */
    private Integer perk0Var3;
    private Integer visionWardsBoughtInGame;
    private Integer altarsNeutralized;
    private Integer pentaKills;
    private Long totalHeal;
    private Integer totalMinionsKilled;
    private Long timeCCingOthers;
}
