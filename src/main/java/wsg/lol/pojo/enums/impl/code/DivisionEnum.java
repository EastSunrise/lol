package wsg.lol.pojo.enums.impl.code;

import wsg.lol.pojo.enums.intf.BaseEnum;

/**
 * @author King
 */
public enum DivisionEnum implements BaseEnum<Integer> {
    I(1),
    II(2),
    III(3),
    IV(4);

    private Integer value;

    DivisionEnum(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
