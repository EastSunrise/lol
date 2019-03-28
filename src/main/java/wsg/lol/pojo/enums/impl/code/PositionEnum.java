package wsg.lol.pojo.enums.impl.code;

import wsg.lol.pojo.enums.intf.BaseEnum;

/**
 * @author King
 */
public enum PositionEnum implements BaseEnum<Integer> {
    NONE(0),
    TOP(1),
    JUNGLE(2),
    MIDDLE(3),
    BOTTOM(4),
    UTILITY(5),
    APEX(6);

    private Integer value;

    PositionEnum(Integer value) {
        this.value = value;
    }

    public static PositionEnum[] positionalValues() {
        return new PositionEnum[]{
                TOP, JUNGLE, MIDDLE, BOTTOM, UTILITY
        };
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
