package wsg.lol.common.enums.match;

import wsg.lol.common.pojo.serialize.IntSerializable;

/**
 * Enum for id of teams in the match.
 *
 * @author Kingen
 */
public enum TeamIdEnum implements IntSerializable {
    Blue(100),
    Red(200);

    private Integer value;

    TeamIdEnum(Integer value) {
        this.value = value;
    }

    @Override
    public int serializeInt() {
        return value;
    }
}
