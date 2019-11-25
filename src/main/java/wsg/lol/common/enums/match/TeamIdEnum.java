package wsg.lol.common.enums.match;

import wsg.lol.common.pojo.parser.JsonSerializable;

/**
 * Enum for id of teams in the match.
 *
 * @author Kingen
 */
public enum TeamIdEnum implements JsonSerializable {
    Blue(100),
    Red(200);

    private Integer value;

    TeamIdEnum(Integer value) {
        this.value = value;
    }

    @Override
    public String serialize() {
        return null;
    }

    public Integer getValue() {
        return value;
    }
}
