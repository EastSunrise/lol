package wsg.lol.common.enums.match;

import wsg.lol.common.pojo.parser.JsonSerializable;

/**
 * Enum for roles of summoners in the match.
 *
 * @author Kingen
 */
public enum MatchRoleEnum implements JsonSerializable {
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
