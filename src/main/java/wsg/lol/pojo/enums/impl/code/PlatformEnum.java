package wsg.lol.pojo.enums.impl.code;

import wsg.lol.pojo.enums.intf.BaseEnum;

/**
 * wsg
 *
 * @author wangsigen
 */
public enum PlatformEnum implements BaseEnum<Integer> {
    NA1(1),
    KR(2),
    ;

    private Integer value;

    PlatformEnum(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
