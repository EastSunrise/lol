package wsg.lol.common.enums.match;

import wsg.lol.common.pojo.serialize.StringSerializable;

/**
 * Enum for types of lane.
 *
 * @author Kingen
 */
public enum LaneTypeEnum implements StringSerializable {
    TOP_LANE,
    MID_LANE,
    BOT_LANE;

    @Override
    public String serialize() {
        return name();
    }
}
