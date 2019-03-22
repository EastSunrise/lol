package wsg.lol.pojo.enums.impl.others;

import wsg.lol.pojo.enums.intf.BaseEnum;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-05 10:23
 */
public enum SpellTypeEnum implements BaseEnum<Integer> {
    SPELL(1),
    PASSIVE(2),
    SUMMONER(3);

    private int value;

    SpellTypeEnum(int value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
