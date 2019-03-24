package wsg.lol.pojo.enums.impl.code;

import wsg.lol.pojo.enums.intf.BaseEnum;

/**
 * wsg
 *
 * @author wangsigen
 */
public enum SeasonEnum implements BaseEnum<Integer> {
    PRESEASON3(0),
    SEASON3(1),
    PRESEASON2014(2),
    SEASON2014(3),
    PRESEASON2015(4),
    SEASON2015(5),
    PRESEASON2016(6),
    SEASON2016(7),
    PRESEASON2017(8),
    SEASON2017(9),
    PRESEASON2018(10),
    SEASON2018(11),
    PRESEASON2019(12),
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
