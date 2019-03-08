package wsg.lol.common.enums.impl.code;

import wsg.lol.common.enums.intf.BaseEnum;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-04 14:43
 */
public enum SeasonEnum implements BaseEnum<Integer> {
    SEASON2018(11),
    SEASON2019(13);

    private int value;

    SeasonEnum(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
