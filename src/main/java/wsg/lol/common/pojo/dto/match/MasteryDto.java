package wsg.lol.common.pojo.dto.match;

import wsg.lol.common.base.BaseDto;

/**
 * // TODO: (Kingen, 2019/11/18)
 *
 * @author EastSunrise
 */
public class MasteryDto extends BaseDto {

    private int masteryId;
    private int rank;

    public int getMasteryId() {
        return masteryId;
    }

    public void setMasteryId(int masteryId) {
        this.masteryId = masteryId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
