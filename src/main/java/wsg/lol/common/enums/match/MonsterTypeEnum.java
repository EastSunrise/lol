package wsg.lol.common.enums.match;

import wsg.lol.common.pojo.parser.JsonSerializable;

/**
 * Enum for types of the monster.
 *
 * @author Kingen
 */
public enum MonsterTypeEnum implements JsonSerializable {
    DRAGON,
    RIFTHERALD,
    BARON_NASHOR;

    @Override
    public String serialize() {
        return name();
    }
}
