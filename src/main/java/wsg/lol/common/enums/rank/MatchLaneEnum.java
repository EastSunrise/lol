package wsg.lol.common.enums.rank;

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
    MID,
    BOTTOM;

    @Override
    public String serialize() {
        return name();
    }
}
