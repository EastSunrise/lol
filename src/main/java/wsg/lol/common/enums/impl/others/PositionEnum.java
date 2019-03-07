package wsg.lol.common.enums.impl.others;

import wsg.lol.common.enums.intf.CodeEnum;

/**
 * @author King
 * @date 2019/2/12
 */
public enum PositionEnum implements CodeEnum {
    NONE("00"),
    TOP("01"),
    JUNGLE("02"),
    MIDDLE("03"),
    BOTTOM("04"),
    UTILITY("05"),
    APEX("06");

    private String code;

    PositionEnum(String code) {
        this.code = code;
    }

    public static PositionEnum[] positionalValues() {
        return new PositionEnum[]{
                TOP, JUNGLE, MIDDLE, BOTTOM, UTILITY
        };
    }

    @Override
    public String getCode() {
        return code;
    }
}
