package wsg.lol.common.enums.match;

import wsg.lol.common.pojo.serialize.JSONSerializable;

/**
 * Enum for types of the monster.
 *
 * @author Kingen
 */
public enum MonsterTypeEnum implements JSONSerializable<String> {
    DRAGON,
    RIFTHERALD,
    BARON_NASHOR;

    @Override
    public String serialize() {
        return name();
    }
}
