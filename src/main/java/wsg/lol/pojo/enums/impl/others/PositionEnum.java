package wsg.lol.pojo.enums.impl.others;

/**
 * @author King
 * @date 2019/2/12
 */
public enum PositionEnum {
    NONE,
    TOP,
    JUNGLE,
    MIDDLE,
    BOTTOM,
    UTILITY,
    APEX;

    public static PositionEnum[] positionalValues() {
        return new PositionEnum[]{
                TOP, JUNGLE, MIDDLE, BOTTOM, UTILITY
        };
    }
}
