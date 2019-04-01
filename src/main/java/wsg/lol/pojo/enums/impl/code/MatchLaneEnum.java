package wsg.lol.pojo.enums.impl.code;

import wsg.lol.pojo.base.BaseEnum;

/**
 * wsg
 *
 * @author wangsigen
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
