package wsg.lol.common.enums.match;

import wsg.lol.common.pojo.serialize.JSONSerializable;

/**
 * Enum for types of building in the match events.
 *
 * @author Kingen
 */
public enum BuildingTypeEnum implements JSONSerializable<String> {
    TOWER_BUILDING,
    INHIBITOR_BUILDING,
    ;

    @Override
    public String serialize() {
        return name();
    }
}
