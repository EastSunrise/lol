package wsg.lol.common.enums.impl.others;

import wsg.lol.common.enums.intf.CodeEnum;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-01 13:38
 */
public enum SpectatorTypeEnum implements CodeEnum {
    NONE("00"),
    LOBBYONLY("01"),
    ALL("02");

    private String code;

    SpectatorTypeEnum(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }
}
