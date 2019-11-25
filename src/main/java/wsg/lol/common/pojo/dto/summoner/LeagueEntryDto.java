package wsg.lol.common.pojo.dto.summoner;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.rank.DivisionEnum;
import wsg.lol.common.enums.rank.RankQueueEnum;
import wsg.lol.common.enums.rank.TierEnum;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Bean for entries of leagues.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(schema = "lol", name = "s_league")
public class LeagueEntryDto extends BaseDto {

    @Id
    private String summonerId;
    @Id
    private RankQueueEnum queueType;
    @Column
    private String leagueId;
    @Column
    private TierEnum tier;
    @Column
    private DivisionEnum rank;

    @Column
    private String summonerName;
    @Column
    private Boolean hotStreak;
    @Column
    private MiniSeriesDto miniSeries;
    @Column
    private Integer wins;
    @Column
    private Boolean veteran;
    @Column
    private Integer losses;
    @Column
    private Boolean inactive;
    @Column
    private Boolean freshBlood;
    @Column
    private Integer leaguePoints;
}
