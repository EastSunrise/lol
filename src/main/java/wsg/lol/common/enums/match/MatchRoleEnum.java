package wsg.lol.common.enums.match;

import wsg.lol.common.pojo.serialize.StringSerializable;

/**
 * Enum for roles of summoners in the match.
 *
 * @author Kingen
 */
public enum MatchRoleEnum implements StringSerializable {
    NONE,
    SOLO,
    DUO,
    DUO_CARRY,
    DUO_SUPPORT;

    @Override
    public String serialize() {
        return name();
    }
}
