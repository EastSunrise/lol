package wsg.lol.common.enums.match;

import wsg.lol.common.pojo.serialize.StringSerializable;

/**
 * Enum for types of the monster.
 *
 * @author Kingen
 */
public enum MonsterTypeEnum implements StringSerializable {
    DRAGON,
    RIFTHERALD,
    BARON_NASHOR;

    @Override
    public String serialize() {
        return name();
    }
}
