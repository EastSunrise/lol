package wsg.lol.common.enums.match;

import wsg.lol.common.pojo.serialize.StringSerializable;

/**
 * Enum for types of leveling up.
 *
 * @author Kingen
 */
public enum LevelUpTypeEnum implements StringSerializable {
    NORMAL,
    EVOLVE,
    ;

    @Override
    public String serialize() {
        return name();
    }
}
