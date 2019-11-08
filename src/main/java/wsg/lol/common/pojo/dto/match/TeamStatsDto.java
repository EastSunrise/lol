package wsg.lol.common.pojo.dto.match;

import wsg.lol.common.pojo.base.BaseDto;

import java.util.List;

/**
 * @author EastSunrise
 */
public class TeamStatsDto extends BaseDto {

    /**
     * Flag indicating whether or not the team scored the first Dragon kill.
     */
    private boolean firstDragon;

    /**
     * Flag indicating whether or not the team destroyed the first inhibitor.
     */
    private boolean firstInhibitor;

    /**
     * If match queueId has a draft, contains banned champion data, otherwise empty.
     */
    private List<TeamBansDto> bans;

    /**
     * Number of times the team killed Baron.
     */
    private int baronKills;

    /**
     * Flag indicating whether or not the team scored the first Rift Herald kill.
     */
    private boolean firstRiftHerald;

    /**
     * Flag indicating whether or not the team scored the first Baron kill.
     */
    private boolean firstBaron;

    /**
     * Number of times the team killed Rift Herald.
     */
    private int riftHeraldKills;

    /**
     * Flag indicating whether or not the team scored the first blood.
     */
    private boolean firstBlood;

    /**
     * 100 for blue side. 200 for red side.
     */
    private int teamId;

    /**
     * Flag indicating whether or not the team destroyed the first tower.
     */
    private boolean firstTower;

    /**
     * Number of times the team killed Vilemaw.
     */
    private int vilemawKills;

    /**
     * Number of inhibitors the team destroyed.
     */
    private int inhibitorKills;

    /**
     * Number of towers the team destroyed.
     */
    private int towerKills;

    /**
     * For Dominion match, specifies the points the team had at game end.
     */
    private int dominionVictoryScore;

    /**
     * String indicating whether or not the team won. There are only two values visibile in public match history.
     * (Legal
     * values: Fail, Win)
     */
    private String win;

    /**
     * Number of times the team killed Dragon.
     */
    private int dragonKills;

    public boolean isFirstDragon() {
        return firstDragon;
    }

    public void setFirstDragon(boolean firstDragon) {
        this.firstDragon = firstDragon;
    }

    public boolean isFirstInhibitor() {
        return firstInhibitor;
    }

    public void setFirstInhibitor(boolean firstInhibitor) {
        this.firstInhibitor = firstInhibitor;
    }

    public List<TeamBansDto> getBans() {
        return bans;
    }

    public void setBans(List<TeamBansDto> bans) {
        this.bans = bans;
    }

    public int getBaronKills() {
        return baronKills;
    }

    public void setBaronKills(int baronKills) {
        this.baronKills = baronKills;
    }

    public boolean isFirstRiftHerald() {
        return firstRiftHerald;
    }

    public void setFirstRiftHerald(boolean firstRiftHerald) {
        this.firstRiftHerald = firstRiftHerald;
    }

    public boolean isFirstBaron() {
        return firstBaron;
    }

    public void setFirstBaron(boolean firstBaron) {
        this.firstBaron = firstBaron;
    }

    public int getRiftHeraldKills() {
        return riftHeraldKills;
    }

    public void setRiftHeraldKills(int riftHeraldKills) {
        this.riftHeraldKills = riftHeraldKills;
    }

    public boolean isFirstBlood() {
        return firstBlood;
    }

    public void setFirstBlood(boolean firstBlood) {
        this.firstBlood = firstBlood;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public boolean isFirstTower() {
        return firstTower;
    }

    public void setFirstTower(boolean firstTower) {
        this.firstTower = firstTower;
    }

    public int getVilemawKills() {
        return vilemawKills;
    }

    public void setVilemawKills(int vilemawKills) {
        this.vilemawKills = vilemawKills;
    }

    public int getInhibitorKills() {
        return inhibitorKills;
    }

    public void setInhibitorKills(int inhibitorKills) {
        this.inhibitorKills = inhibitorKills;
    }

    public int getTowerKills() {
        return towerKills;
    }

    public void setTowerKills(int towerKills) {
        this.towerKills = towerKills;
    }

    public int getDominionVictoryScore() {
        return dominionVictoryScore;
    }

    public void setDominionVictoryScore(int dominionVictoryScore) {
        this.dominionVictoryScore = dominionVictoryScore;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public int getDragonKills() {
        return dragonKills;
    }

    public void setDragonKills(int dragonKills) {
        this.dragonKills = dragonKills;
    }
}
