package wsg.lol.common.enums.match;

import wsg.lol.dao.common.serialize.JSONSerializable;

/**
 * Enum for types of warding.
 *
 * @author Kingen
 */
public enum WardTypeEnum implements JSONSerializable<String> {
    YELLOW_TRINKET,
    CONTROL_WARD,
    SIGHT_WARD,
    BLUE_TRINKET,
    TEEMO_MUSHROOM,
    UNDEFINED;

    @Override
    public String serialize() {
        return name();
    }
}
