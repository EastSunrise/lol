package wsg.lol.pojo.dto.api.match;

/**
 * @author King
 */
public class TeamBansDto {
    /**
     * Turn during which the champion was banned.
     */
    private int pickTurn;

    /**
     * Banned championId.
     */
    private int championId;

    public int getPickTurn() {
        return pickTurn;
    }

    public void setPickTurn(int pickTurn) {
        this.pickTurn = pickTurn;
    }

    public int getChampionId() {
        return championId;
    }

    public void setChampionId(int championId) {
        this.championId = championId;
    }
}
