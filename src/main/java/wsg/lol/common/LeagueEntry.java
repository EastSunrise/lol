package wsg.lol.common;

public class LeagueEntry extends LeagueEntryKey {
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

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId == null ? null : leagueId.trim();
    }

    public Byte getTier() {
        return tier;
    }

    public void setTier(Byte tier) {
        this.tier = tier;
    }

    public Byte getRank() {
        return rank;
    }

    public void setRank(Byte rank) {
        this.rank = rank;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName == null ? null : summonerName.trim();
    }

    public Byte getHotStreak() {
        return hotStreak;
    }

    public void setHotStreak(Byte hotStreak) {
        this.hotStreak = hotStreak;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Byte getVeteran() {
        return veteran;
    }

    public void setVeteran(Byte veteran) {
        this.veteran = veteran;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public Byte getInactive() {
        return inactive;
    }

    public void setInactive(Byte inactive) {
        this.inactive = inactive;
    }

    public Byte getFreshBlood() {
        return freshBlood;
    }

    public void setFreshBlood(Byte freshBlood) {
        this.freshBlood = freshBlood;
    }

    public Integer getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(Integer leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public Integer getSeriesTarget() {
        return seriesTarget;
    }

    public void setSeriesTarget(Integer seriesTarget) {
        this.seriesTarget = seriesTarget;
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