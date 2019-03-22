package wsg.lol.pojo.enums.impl.code;

import wsg.lol.pojo.enums.intf.BaseEnum;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-04 14:56
 */
public enum MatchQueueEnum implements BaseEnum<Integer> {
    RANKED_SOLO_GAMES_5v5(420),
    BLIND_PICK_GAMES_5v5(430),
    RANKED_FLEX_GAMES_5v5(440),
    ARAM_GAMES_5v5(450),
    BLIND_PICK_GAMES_3v3(460),
    RANKED_FLEX_GAMES_3v3(470),
    ;

    private int value;

    MatchQueueEnum(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
