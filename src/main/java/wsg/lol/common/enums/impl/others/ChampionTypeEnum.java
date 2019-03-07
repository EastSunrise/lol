package wsg.lol.common.enums.impl.others;

import wsg.lol.common.enums.intf.CodeEnum;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-02-27 15:29
 */
public enum ChampionTypeEnum implements CodeEnum {
    ;

    private String code;

    ChampionTypeEnum(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }
}
