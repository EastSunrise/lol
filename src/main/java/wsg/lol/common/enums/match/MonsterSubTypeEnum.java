package wsg.lol.common.enums.match;

import wsg.lol.common.pojo.serialize.StringSerializable;

/**
 * Enum for sub types of the monster.
 *
 * @author Kingen
 */
public enum MonsterSubTypeEnum implements StringSerializable {
    WATER_DRAGON,
    FIRE_DRAGON,
    AIR_DRAGON;

    @Override
    public String serialize() {
        return name();
    }
}
