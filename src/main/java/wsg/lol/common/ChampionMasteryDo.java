package wsg.lol.common;

import java.util.Date;

public class ChampionMasteryDo extends ChampionMasteryDoKey {
    private Byte chestGranted;

    private Integer championLevel;

    private Integer championPoints;

    private Integer championPointsUntilNextLevel;

    private Integer championPointsSinceLastLevel;

    private Integer tokensEarned;

    private Date lastPlayTime;

    public Byte getChestGranted() {
        return chestGranted;
    }

    public void setChestGranted(Byte chestGranted) {
        this.chestGranted = chestGranted;
    }

    public Integer getChampionLevel() {
        return championLevel;
    }

    public void setChampionLevel(Integer championLevel) {
        this.championLevel = championLevel;
    }

    public Integer getChampionPoints() {
        return championPoints;
    }

    public void setChampionPoints(Integer championPoints) {
        this.championPoints = championPoints;
    }

    public Integer getChampionPointsUntilNextLevel() {
        return championPointsUntilNextLevel;
    }

    public void setChampionPointsUntilNextLevel(Integer championPointsUntilNextLevel) {
        this.championPointsUntilNextLevel = championPointsUntilNextLevel;
    }

    public Integer getChampionPointsSinceLastLevel() {
        return championPointsSinceLastLevel;
    }

    public void setChampionPointsSinceLastLevel(Integer championPointsSinceLastLevel) {
        this.championPointsSinceLastLevel = championPointsSinceLastLevel;
    }

    public Integer getTokensEarned() {
        return tokensEarned;
    }

    public void setTokensEarned(Integer tokensEarned) {
        this.tokensEarned = tokensEarned;
    }

    public Date getLastPlayTime() {
        return lastPlayTime;
    }

    public void setLastPlayTime(Date lastPlayTime) {
        this.lastPlayTime = lastPlayTime;
    }
}