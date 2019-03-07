package wsg.lol.common.enums.impl.others;

import wsg.lol.common.enums.intf.CodeEnum;

/**
 * @author King
 * @date 2019/2/12
 */
public enum RankQueueEnum implements CodeEnum {
    RANKED_SOLO_5x5("01"),
    RANKED_FLEX_SR("02"),
    RANKED_FLEX_TT("03");

    private String code;

    RankQueueEnum(String code) {
        this.code = code;
    }

    public static RankQueueEnum[] positionalValues() {
        return new RankQueueEnum[]{
                RANKED_SOLO_5x5
        };
    }

    @Override
    public String getCode() {
        return code;
    }
}
