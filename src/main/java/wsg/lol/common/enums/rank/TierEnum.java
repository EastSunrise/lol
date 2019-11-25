package wsg.lol.common.enums.rank;

/**
 * Enum for tiers.
 *
 * @author Kingen
 */
public enum TierEnum {
    CHALLENGER,
    GRANDMASTER,
    MASTER,
    DIAMOND,
    PLATINUM,
    GOLD,
    SILVER,
    BRONZE,
    IRON,
    UNRANKED;

    public static final TierEnum[] APEX_TIERS = new TierEnum[]{
            CHALLENGER, GRANDMASTER, MASTER
    };

    public static final TierEnum[] RANKED_TIERS = new TierEnum[]{
            CHALLENGER, GRANDMASTER, MASTER,
            DIAMOND, PLATINUM, GOLD, SILVER, BRONZE, IRON
    };
}
