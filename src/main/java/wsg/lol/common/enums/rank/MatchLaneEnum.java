package wsg.lol.common.enums.rank;

import wsg.lol.common.base.BaseEnum;

/**
 * wsg
 *
 * @author EastSunrise
 */
public enum MatchLaneEnum implements BaseEnum<Integer> {
    NONE(0),
    TOP(1),
    JUNGLE(2),
    MID(3),
    BOTTOM(4);

    private int value;

    MatchLaneEnum(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
