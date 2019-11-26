package wsg.lol.common.enums.match;

import wsg.lol.common.pojo.parser.JsonSerializable;

/**
 * Enum for types of lane.
 *
 * @author Kingen
 */
public enum LaneTypeEnum implements JsonSerializable {
    TOP_LANE,
    MID_LANE,
    BOT_LANE;

    @Override
    public String serialize() {
        return name();
    }
}
