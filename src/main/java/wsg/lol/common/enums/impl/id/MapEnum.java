package wsg.lol.common.enums.impl.id;

import wsg.lol.common.enums.intf.IdEnum;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-04 15:00
 */
public enum MapEnum implements IdEnum {
    TwistedTreeline(10),
    SummonersRift(11),
    HowlingAbyss(12);

    private int id;

    MapEnum(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }
}
