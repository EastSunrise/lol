package wsg.lol.common.enums.impl.others;

import wsg.lol.common.enums.intf.CodeEnum;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-05 10:23
 */
public enum SpellTypeEnum implements CodeEnum {
    SPELL("01"),
    PASSIVE("02"),
    SUMMONER("03");

    private String code;

    SpellTypeEnum(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }
}
