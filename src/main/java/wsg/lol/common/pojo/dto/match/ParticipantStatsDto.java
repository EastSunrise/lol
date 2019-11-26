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

    private Integer participantId;

    private Boolean win;

    private Integer item0;
    private Integer item1;
    private Integer item2;
    private Integer item3;
    private Integer item4;
    private Integer item5;
    private Integer item6;

    private Integer kills;
    private Integer deaths;
    private Integer assists;

    private Integer largestKillingSpree;
    private Integer largestMultiKill;
    private Integer killingSprees;
    private Integer longestTimeSpentLiving;
    private Integer doubleKills;
    private Integer tripleKills;
    private Integer quadraKills;
    private Integer pentaKills;
    private Integer unrealKills;

    private Long totalDamageDealt;
    private Long magicDamageDealt;
    private Long physicalDamageDealt;
    private Long trueDamageDealt;
    private Integer largestCriticalStrike;
    private Long totalDamageDealtToChampions;
    private Long magicDamageDealtToChampions;
    private Long physicalDamageDealtToChampions;
    private Long trueDamageDealtToChampions;

    private Long totalHeal;
    private Integer totalUnitsHealed;
    private Long damageSelfMitigated;
    private Long damageDealtToObjectives;
    private Long damageDealtToTurrets;

    private Long visionScore;
    private Integer visionWardsBoughtInGame;
    private Integer sightWardsBoughtInGame;
    private Integer wardsPlaced;
    private Integer wardsKilled;

    private Long totalDamageTaken;
    private Long magicalDamageTaken;
    private Long physicalDamageTaken;
    private Long trueDamageTaken;

    private Integer goldEarned;
    private Integer goldSpent;

    private Integer turretKills;
    private Integer inhibitorKills;
    private Integer totalMinionsKilled;
    private Integer neutralMinionsKilled;
    private Integer neutralMinionsKilledTeamJungle;
    private Integer neutralMinionsKilledEnemyJungle;

    private Integer totalTimeCrowdControlDealt;

    private Integer champLevel;

    private Long timeCCingOthers;

    private Boolean firstBloodKill;
    private Boolean firstBloodAssist;
    private Boolean firstTowerKill;
    private Boolean firstTowerAssist;
    private Boolean firstInhibitorKill;
    private Boolean firstInhibitorAssist;

    private Integer combatPlayerScore;
    private Integer objectivePlayerScore;
    private Integer totalPlayerScore;
    private Integer totalScoreRank;

    private Integer playerScore0;
    private Integer playerScore1;
    private Integer playerScore2;
    private Integer playerScore3;
    private Integer playerScore4;
    private Integer playerScore5;
    private Integer playerScore6;
    private Integer playerScore7;
    private Integer playerScore8;
    private Integer playerScore9;

    private Integer perk0;
    private Integer perk0Var1;
    private Integer perk0Var2;
    private Integer perk0Var3;
    private Integer perk1;
    private Integer perk1Var1;
    private Integer perk1Var2;
    private Integer perk1Var3;
    private Integer perk2;
    private Integer perk2Var1;
    private Integer perk2Var2;
    private Integer perk2Var3;
    private Integer perk3;
    private Integer perk3Var1;
    private Integer perk3Var2;
    private Integer perk3Var3;
    private Integer perk4;
    private Integer perk4Var1;
    private Integer perk4Var2;
    private Integer perk4Var3;
    private Integer perk5;
    private Integer perk5Var1;
    private Integer perk5Var2;
    private Integer perk5Var3;

    private Integer perkPrimaryStyle;
    private Integer perkSubStyle;
    private Integer statPerk0;
    private Integer statPerk1;
    private Integer statPerk2;
}
