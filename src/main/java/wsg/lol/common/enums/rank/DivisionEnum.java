package wsg.lol.common.enums.rank;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Enum for divisions.
 *
 * @author Kingen
 */
public enum DivisionEnum {
    I,
    II,
    III,
    IV;

    public static DivisionEnum[] validDivisions(TierEnum tier) {
        if (ArrayUtils.contains(TierEnum.APEX_TIERS, tier)) {
            return new DivisionEnum[]{
                    I
            };
        }
        return values();
    }

    public boolean isValidDivision(TierEnum tier) {
        return ArrayUtils.contains(validDivisions(tier), this);
    }
}
