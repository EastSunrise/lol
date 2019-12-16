package wsg.lol.common.enums.summoner;

import wsg.lol.common.pojo.serialize.JSONSerializable;

/**
 * Enum for tiers.
 *
 * @author Kingen
 */
public enum TierEnum implements JSONSerializable<String> {
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

    @Override
    public String serialize() {
        return name();
    }
}
