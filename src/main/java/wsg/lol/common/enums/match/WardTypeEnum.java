package wsg.lol.common.enums.match;

import wsg.lol.common.pojo.serialize.StringSerializable;

/**
 * Enum for types of warding.
 *
 * @author Kingen
 */
public enum WardTypeEnum implements StringSerializable {
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
