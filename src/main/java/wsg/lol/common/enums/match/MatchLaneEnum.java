package wsg.lol.common.enums.match;

import wsg.lol.common.pojo.parser.JsonSerializable;

/**
 * Enum for lanes in the match.
 *
 * @author Kingen
 */
public enum MatchLaneEnum implements JsonSerializable {
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
