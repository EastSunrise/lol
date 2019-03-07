package wsg.lol.common.enums.impl.id;

import wsg.lol.common.enums.intf.IdEnum;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-04 14:56
 */
public enum MatchQueueEnum implements IdEnum {
    RANKED_SOLO_GAMES_5v5(420),
    BLIND_PICK_GAMES_5v5(430),
    RANKED_FLEX_GAMES_5v5(440),
    ARAM_GAMES_5v5(450),
    BLIND_PICK_GAMES_3v3(460),
    RANKED_FLEX_GAMES_3v3(470),
    ;

    private int id;

    MatchQueueEnum(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }
}
