package wsg.lol.common.enums.route;

import wsg.lol.common.base.BaseEnum;

/**
 * Enums for route of platform.
 *
 * @author EastSunrise
 */
@Deprecated
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
