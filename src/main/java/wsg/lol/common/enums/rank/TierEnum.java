package wsg.lol.common.enums.rank;

/**
 * // TODO: (Kingen, 2019/11/18)
 *
 * @author EastSunrise
 */
public enum TierEnum {
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

    public Integer getValue() {
        return value;
    }
}
