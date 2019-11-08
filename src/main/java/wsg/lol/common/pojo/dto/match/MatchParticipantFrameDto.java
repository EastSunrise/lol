package wsg.lol.common.pojo.dto.match;

/**
 * @author EastSunrise
 */
public class MatchParticipantFrameDto {

    private int totalGold;
    private int teamScore;
    private int participantId;
    private int level;
    private int currentGold;
    private int minionsKilled;
    private int dominionScore;
    private MatchPositionDto position;
    private int xp;
    private int jungleMinionsKilled;

    public int getTotalGold() {
        return totalGold;
    }

    public void setTotalGold(int totalGold) {
        this.totalGold = totalGold;
    }

    public int getTeamScore() {
        return teamScore;
    }

    public void setTeamScore(int teamScore) {
        this.teamScore = teamScore;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCurrentGold() {
        return currentGold;
    }

    public void setCurrentGold(int currentGold) {
        this.currentGold = currentGold;
    }

    public int getMinionsKilled() {
        return minionsKilled;
    }

    public void setMinionsKilled(int minionsKilled) {
        this.minionsKilled = minionsKilled;
    }

    public int getDominionScore() {
        return dominionScore;
    }

    public void setDominionScore(int dominionScore) {
        this.dominionScore = dominionScore;
    }

    public MatchPositionDto getPosition() {
        return position;
    }

    public void setPosition(MatchPositionDto position) {
        this.position = position;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getJungleMinionsKilled() {
        return jungleMinionsKilled;
    }

    public void setJungleMinionsKilled(int jungleMinionsKilled) {
        this.jungleMinionsKilled = jungleMinionsKilled;
    }
}
