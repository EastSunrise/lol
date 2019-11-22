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
    IRON;

    public static final TierEnum[] APEX_TIERS = new TierEnum[]{
            CHALLENGER, GRANDMASTER, MASTER
    };
}
