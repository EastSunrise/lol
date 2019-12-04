package wsg.lol.common.enums.match;

import wsg.lol.dao.common.serialize.JSONSerializable;

/**
 * Enum for lanes in the match.
 *
 * @author Kingen
 */
public enum MatchLaneEnum implements JSONSerializable<String> {
    NONE,
    TOP,
    JUNGLE,
    MIDDLE,
    MID,
    BOTTOM;

    @Override
    public String serialize() {
        return name();
    }
}
