package wsg.lol.common.enums.impl.others;

import wsg.lol.common.enums.intf.CodeEnum;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-01 13:39
 */
public enum PickTypeEnum implements CodeEnum {
    BLIND_PICK("00"),
    DRAFT_MODE("01"),
    ALL_RANDOM("02"),
    TOURNAMENT_DRAFT("03");

    private String code;

    PickTypeEnum(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }
}
