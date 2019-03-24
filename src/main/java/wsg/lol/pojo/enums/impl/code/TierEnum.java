package wsg.lol.pojo.enums.impl.code;

import wsg.lol.pojo.enums.intf.BaseEnum;

/**
 * @author King
 */
public enum TierEnum implements BaseEnum<Integer> {
    UNRANKED(0),
    CHALLENGER(1),
    GRANDMASTER(2),
    MASTER(3),
    DIAMOND(4),
    PLATINUM(5),
    GOLD(6),
    SILVER(7),
    BRONZE(8),
    IRON(9);

    private int value;

    TierEnum(int value) {
        this.value = value;
    }

    public static TierEnum[] positionalValues() {
        return new TierEnum[]{
                DIAMOND, PLATINUM, GOLD, SILVER, BRONZE, IRON
        };
    }

    public static TierEnum[] apexValues() {
        return new TierEnum[]{
                CHALLENGER, GRANDMASTER, MASTER
        };
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
