package wsg.lol.common.enums.match;

import wsg.lol.common.pojo.parser.JsonSerializable;

/**
 * Enum for types of leveling up.
 *
 * @author Kingen
 */
public enum LevelUpTypeEnum implements JsonSerializable {
    NORMAL,
    EVOLVE,
    ;

    @Override
    public String serialize() {
        return name();
    }
}
