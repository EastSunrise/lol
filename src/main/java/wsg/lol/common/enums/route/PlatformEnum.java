package wsg.lol.common.enums.route;

import wsg.lol.common.pojo.base.BaseEnum;

/**
 * wsg
 *
 * @author EastSunrise
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
