package wsg.lol.common.pojo.dto.match;

import wsg.lol.common.base.BaseDto;

/**
 * // TODO: (Kingen, 2019/11/18)
 * @author EastSunrise
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
