package wsg.lol.pojo.dto.api.match;

import wsg.lol.pojo.base.BaseDto;

/**
 * @author King
 */
public class RuneDto extends BaseDto {

    private int runeId;
    private int rank;

    public int getRuneId() {
        return runeId;
    }

    public void setRuneId(int runeId) {
        this.runeId = runeId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
