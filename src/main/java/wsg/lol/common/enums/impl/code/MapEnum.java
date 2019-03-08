package wsg.lol.common.enums.impl.code;

import wsg.lol.common.enums.intf.BaseEnum;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-04 15:00
 */
public enum MapEnum implements BaseEnum<Integer> {
    TwistedTreeline(10),
    SummonersRift(11),
    HowlingAbyss(12);

    private int value;

    MapEnum(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
