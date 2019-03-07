package wsg.lol.dmo.league;


import wsg.lol.common.base.BaseDmo;
import wsg.lol.common.enums.impl.name.TierEnum;
import wsg.lol.common.enums.impl.others.DivisionEnum;
import wsg.lol.common.enums.impl.others.PositionEnum;
import wsg.lol.common.enums.impl.others.RankQueueEnum;

public class PositionDmo extends BaseDmo {

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
    private Integer seriesTarget;
    private Integer seriesWins;
    private Integer seriesLosses;


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

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }


    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }


    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
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

    public Integer getSeriesTarget() {
        return seriesTarget;
    }

    public void setSeriesTarget(Integer seriesTarget) {
        this.seriesTarget = seriesTarget;
    }

    public RankQueueEnum getQueue() {
        return queue;
    }

    public void setQueue(RankQueueEnum queue) {
        this.queue = queue;
    }

    public PositionEnum getPosition() {
        return position;
    }

    public void setPosition(PositionEnum position) {
        this.position = position;
    }

    public TierEnum getTier() {
        return tier;
    }

    public void setTier(TierEnum tier) {
        this.tier = tier;
    }

    public DivisionEnum getRank() {
        return rank;
    }

    public void setRank(DivisionEnum rank) {
        this.rank = rank;
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

    public Integer getSeriesWins() {
        return seriesWins;
    }

    public void setSeriesWins(Integer seriesWins) {
        this.seriesWins = seriesWins;
    }

    public Integer getSeriesLosses() {
        return seriesLosses;
    }

    public void setSeriesLosses(Integer seriesLosses) {
        this.seriesLosses = seriesLosses;
    }

}
