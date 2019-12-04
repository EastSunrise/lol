package wsg.lol.common.enums.match;

import wsg.lol.dao.common.serialize.JSONSerializable;

/**
 * Enum for roles of summoners in the match.
 *
 * @author Kingen
 */
public enum MatchRoleEnum implements JSONSerializable<String> {
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
