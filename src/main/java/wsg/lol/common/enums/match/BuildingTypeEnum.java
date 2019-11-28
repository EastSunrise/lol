package wsg.lol.common.enums.match;

import wsg.lol.common.pojo.serialize.StringSerializable;

/**
 * Enum for types of building in the match events.
 *
 * @author Kingen
 */
public enum BuildingTypeEnum implements StringSerializable {
    TOWER_BUILDING,
    INHIBITOR_BUILDING,
    ;

    @Override
    public String serialize() {
        return name();
    }
}
