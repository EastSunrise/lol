package wsg.lol.pojo.enums.impl.others;

import wsg.lol.pojo.enums.intf.BaseEnum;

/**
 * @author King
 * @date 2019/2/12
 */
public enum RankQueueEnum implements BaseEnum<Integer> {
    RANKED_SOLO_5x5(1),
    RANKED_FLEX_SR(2),
    RANKED_FLEX_TT(3);

    private int value;

    RankQueueEnum(int value) {
        this.value = value;
    }

    public static RankQueueEnum[] positionalValues() {
        return new RankQueueEnum[]{};
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
