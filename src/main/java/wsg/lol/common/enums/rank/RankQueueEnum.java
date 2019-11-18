package wsg.lol.common.enums.rank;

import wsg.lol.common.base.BaseEnum;

/**
 * // TODO: (Kingen, 2019/11/18)
 *
 * @author Kingen
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
