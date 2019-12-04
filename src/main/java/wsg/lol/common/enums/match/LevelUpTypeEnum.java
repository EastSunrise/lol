package wsg.lol.common.enums.match;

import wsg.lol.dao.common.serialize.JSONSerializable;

/**
 * Enum for types of leveling up.
 *
 * @author Kingen
 */
public enum LevelUpTypeEnum implements JSONSerializable<String> {
    NORMAL,
    EVOLVE,
    ;

    @Override
    public String serialize() {
        return name();
    }
}
