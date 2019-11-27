package wsg.lol.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDo;

import javax.persistence.Table;

/**
 * DO for entries of leagues.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(schema = "lol", name = "s_league")
public class LeagueEntryDo extends BaseDo {

    private String summonerId;

    private Byte queueType;

    private String leagueId;

    private Byte tier;

    private Byte rank;

    private String summonerName;

    private Byte hotStreak;

    private Integer wins;

    private Byte veteran;

    private Integer losses;

    private Byte inactive;

    private Byte freshBlood;

    private Integer leaguePoints;

    private Integer seriesTarget;

    private Integer seriesWins;

    private Integer seriesLosses;

    private String seriesProgress;
}