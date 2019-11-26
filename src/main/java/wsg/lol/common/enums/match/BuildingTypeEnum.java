package wsg.lol.common.enums.match;

import wsg.lol.common.pojo.parser.JsonSerializable;

/**
 * Enum for types of building in the match events.
 *
 * @author Kingen
 */
public enum BuildingTypeEnum implements JsonSerializable {
    TOWER_BUILDING,
    INHIBITOR_BUILDING,
    ;

    @Override
    public String serialize() {
        return name();
    }
}
