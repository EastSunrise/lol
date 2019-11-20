package wsg.lol.common.pojo.dmo.league;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.rank.DivisionEnum;
import wsg.lol.common.enums.rank.PositionEnum;
import wsg.lol.common.enums.rank.RankQueueEnum;
import wsg.lol.common.enums.rank.TierEnum;

/**
 * Bean for entries of leagues.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LeagueEntryDto extends BaseDto {

    private Integer id;
    private String summonerId;
    private RankQueueEnum queue;
    private PositionEnum position;
    private String summonerName;
    private String leagueId;
    private String leagueName;
    private TierEnum tier;
    private DivisionEnum rank;
    private Integer leaguePoints;
    private Integer wins;
    private Integer losses;
    private Boolean veteran;
    private Boolean inactive;
    private Boolean freshBlood;
    private Boolean hotStreak;
    private Integer miniSeriesTarget;
    private Integer miniSeriesWins;
    private Integer miniSeriesLosses;
}
