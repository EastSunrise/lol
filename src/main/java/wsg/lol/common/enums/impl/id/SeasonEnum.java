package wsg.lol.common.enums.impl.id;

import wsg.lol.common.enums.intf.IdEnum;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-04 14:43
 */
public enum SeasonEnum implements IdEnum {
    SEASON2018(11),
    SEASON2019(13);

    private int id;

    SeasonEnum(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }
}
