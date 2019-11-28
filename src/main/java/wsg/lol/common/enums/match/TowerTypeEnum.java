package wsg.lol.common.enums.match;

import wsg.lol.common.pojo.serialize.StringSerializable;

/**
 * Enum for types of towers.
 *
 * @author Kingen
 */
public enum TowerTypeEnum implements StringSerializable {
    OUTER_TURRET,
    INNER_TURRET,
    BASE_TURRET,
    NEXUS_TURRET,
    UNDEFINED_TURRET;

    @Override
    public String serialize() {
        return name();
    }
}
