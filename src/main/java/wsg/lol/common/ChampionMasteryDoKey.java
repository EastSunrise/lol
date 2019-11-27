package wsg.lol.common;

public class ChampionMasteryDoKey {
    private String summonerId;

    private Integer championId;

    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId == null ? null : summonerId.trim();
    }

    public Integer getChampionId() {
        return championId;
    }

    public void setChampionId(Integer championId) {
        this.championId = championId;
    }
}