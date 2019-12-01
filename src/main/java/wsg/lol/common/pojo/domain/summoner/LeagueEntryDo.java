package wsg.lol.common.pojo.domain.summoner;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.annotation.Flatten;
import wsg.lol.common.base.BaseDo;
import wsg.lol.common.enums.share.RankQueueEnum;
import wsg.lol.common.enums.summoner.DivisionEnum;
import wsg.lol.common.enums.summoner.TierEnum;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DO for entries of leagues.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "s_league")
public class LeagueEntryDo extends BaseDo {

    @Id
    private String summonerId;

    @Id
    @Column
    private RankQueueEnum queueType;

    @Column
    private String leagueId;

    @Column
    private TierEnum tier;

    @Column(name = "`rank`")
    private DivisionEnum rank;

    @Column
    private String summonerName;

    @Column
    private Boolean hotStreak;

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

    @Column
    @Flatten
    private Integer seriesTarget;

    @Column
    @Flatten
    private Integer seriesWins;

    @Column
    @Flatten
    private Integer seriesLosses;

    @Column
    @Flatten
    private String seriesProgress;
}