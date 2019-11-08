package wsg.lol.common.enums.rank;

/**
 * @author EastSunrise
 */
public enum PositionEnum {
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

    public Integer getValue() {
        return value;
    }
}
