package wsg.lol.common.pojo.dto.league;


import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.game.DivisionEnum;

/**
 * Bean for items in the league.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
class LeagueItemDto extends BaseDto {

    private Integer id;

    private String leagueId;
    private DivisionEnum rank;

    private String summonerName;
    private Boolean hotStreak;
    private MiniSeriesDto miniSeries;
    private Integer wins;
    private Boolean veteran;
    private Integer losses;
    private Boolean freshBlood;
    private Boolean inactive;
    private String summonerId;
    private Integer leaguePoints;
}
