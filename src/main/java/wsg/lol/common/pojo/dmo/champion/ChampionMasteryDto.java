package wsg.lol.common.pojo.dmo.champion;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

import java.util.Date;

/**
 * Bean for champion mastery of summoners.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ChampionMasteryDto extends BaseDto {

    private Integer id;

    private String summonerId;
    private Integer championId;
    private Boolean chestGranted;
    private Integer championLevel;
    private Integer championPoints;
    private Integer championPointsUntilNextLevel;
    private Integer championPointsSinceLastLevel;
    private Integer tokensEarned;
    private Date lastPlayTime;

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

    public Integer getChampionId() {
        return championId;
    }

    public void setChampionId(Integer championId) {
        this.championId = championId;
    }

    public Boolean getChestGranted() {
        return chestGranted;
    }

    public void setChestGranted(Boolean chestGranted) {
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
