package wsg.lol.pojo.enums.impl.code;

import wsg.lol.pojo.enums.intf.BaseEnum;

/**
 * wsg
 *
 * @author wangsigen
 */
public enum GameTypeEnum implements BaseEnum<Integer> {
    MATCHED_GAME(1);

    private int value;

    GameTypeEnum(int value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
