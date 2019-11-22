package wsg.lol.common.pojo.dto.summoner;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.rank.DivisionEnum;
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

    private String summonerId;
    private RankQueueEnum queueType;
    private String leagueId;
    private TierEnum tier;
    private DivisionEnum rank;

    private String summonerName;
    private Boolean hotStreak;
    private MiniSeriesDto miniSeries;
    private Integer wins;
    private Boolean veteran;
    private Integer losses;
    private Boolean inactive;
    private Boolean freshBlood;
    private Integer leaguePoints;
}
