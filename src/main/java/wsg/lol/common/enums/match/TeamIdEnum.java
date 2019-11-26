package wsg.lol.common.enums.match;

import wsg.lol.common.pojo.parser.IntegerSerializable;

/**
 * Enum for id of teams in the match.
 *
 * @author Kingen
 */
public enum TeamIdEnum implements IntegerSerializable {
    Blue(100),
    Red(200);

    private Integer value;

    TeamIdEnum(Integer value) {
        this.value = value;
    }

    @Override
    public Integer serializeInteger() {
        return value;
    }
}
