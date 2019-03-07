package wsg.lol.dto.api.spectator;

/**
 * @author King
 * @date 2019/2/12
 */
public class BannedChampion {

    /**
     * The turn during which the champion was banned
     */
    private int pickTurn;

    /**
     * The ID of the banned champion
     */
    private long championId;

    /**
     * The ID of the team that banned the champion
     */
    private long teamId;

    public int getPickTurn() {
        return pickTurn;
    }

    public void setPickTurn(int pickTurn) {
        this.pickTurn = pickTurn;
    }

    public long getChampionId() {
        return championId;
    }

    public void setChampionId(long championId) {
        this.championId = championId;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }
}
