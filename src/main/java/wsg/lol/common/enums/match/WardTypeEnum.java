package wsg.lol.common.enums.match;

import wsg.lol.common.pojo.parser.JsonSerializable;

/**
 * Enum for types of warding.
 *
 * @author Kingen
 */
public enum WardTypeEnum implements JsonSerializable {
    YELLOW_TRINKET,
    CONTROL_WARD,
    SIGHT_WARD,
    BLUE_TRINKET,
    UNDEFINED;

    @Override
    public String serialize() {
        return name();
    }
}
