package wsg.lol.common.enums.impl.name;

/**
 * @author King
 * @date 2019/2/12
 */
public enum TierEnum {
    UNRANKED,
    CHALLENGER,
    GRANDMASTER,
    MASTER,
    DIAMOND,
    PLATINUM,
    GOLD,
    SILVER,
    BRONZE,
    IRON;

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
}
