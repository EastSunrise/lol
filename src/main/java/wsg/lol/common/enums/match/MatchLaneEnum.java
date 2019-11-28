package wsg.lol.common.enums.match;

import wsg.lol.common.pojo.serialize.StringSerializable;

/**
 * Enum for lanes in the match.
 *
 * @author Kingen
 */
public enum MatchLaneEnum implements StringSerializable {
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
