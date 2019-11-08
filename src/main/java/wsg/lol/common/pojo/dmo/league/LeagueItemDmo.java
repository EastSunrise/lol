package wsg.lol.common.pojo.dmo.league;

import tk.mybatis.mapper.annotation.KeySql;
import wsg.lol.common.enums.rank.DivisionEnum;
import wsg.lol.common.pojo.base.BaseDmo;
import wsg.lol.common.pojo.base.Persistable;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * wsg
 *
 * @author EastSunrise
 */
@Table(name = "l_item")
public class LeagueItemDmo extends BaseDmo implements Persistable {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    private String summonerId;
    private String leagueId;
    private String summonerName;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public DivisionEnum getRank() {
        return rank;
    }

    public void setRank(DivisionEnum rank) {
        this.rank = rank;
    }

    public Integer getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(Integer leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public Boolean getVeteran() {
        return veteran;
    }

    public void setVeteran(Boolean veteran) {
        this.veteran = veteran;
    }

    public Boolean getInactive() {
        return inactive;
    }

    public void setInactive(Boolean inactive) {
        this.inactive = inactive;
    }

    public Boolean getFreshBlood() {
        return freshBlood;
    }

    public void setFreshBlood(Boolean freshBlood) {
        this.freshBlood = freshBlood;
    }

    public Boolean getHotStreak() {
        return hotStreak;
    }

    public void setHotStreak(Boolean hotStreak) {
        this.hotStreak = hotStreak;
    }

    public Integer getMiniSeriesTarget() {
        return miniSeriesTarget;
    }

    public void setMiniSeriesTarget(Integer miniSeriesTarget) {
        this.miniSeriesTarget = miniSeriesTarget;
    }

    public Integer getMiniSeriesWins() {
        return miniSeriesWins;
    }

    public void setMiniSeriesWins(Integer miniSeriesWins) {
        this.miniSeriesWins = miniSeriesWins;
    }

    public Integer getMiniSeriesLosses() {
        return miniSeriesLosses;
    }

    public void setMiniSeriesLosses(Integer miniSeriesLosses) {
        this.miniSeriesLosses = miniSeriesLosses;
    }
}
