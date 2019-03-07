package wsg.lol.common.enums.impl.others;

import wsg.lol.common.enums.intf.CodeEnum;

/**
 * @author King
 * @date 2019/2/12
 */
public enum DivisionEnum implements CodeEnum {
    I("01"),
    II("02"),
    III("03"),
    IV("04");

    private String code;

    DivisionEnum(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }
}
