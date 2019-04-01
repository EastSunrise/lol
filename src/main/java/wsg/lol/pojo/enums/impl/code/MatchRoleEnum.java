package wsg.lol.pojo.enums.impl.code;

import wsg.lol.pojo.base.BaseEnum;

/**
 * wsg
 *
 * @author wangsigen
 */
public enum MatchRoleEnum implements BaseEnum<Integer> {
    NONE(0),
    SOLO(1),
    DUO(2),
    DUO_CARRY(3),
    DUO_SUPPORT(4);

    private int value;

    MatchRoleEnum(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
