package wsg.lol.common.enums.match;

import wsg.lol.dao.common.serialize.JSONSerializable;

/**
 * Enum for sub types of the monster.
 *
 * @author Kingen
 */
public enum MonsterSubTypeEnum implements JSONSerializable<String> {
    WATER_DRAGON,
    FIRE_DRAGON,
    AIR_DRAGON,
    EARTH_DRAGON,
    ELDER_DRAGON,
    ;

    @Override
    public String serialize() {
        return name();
    }
}
