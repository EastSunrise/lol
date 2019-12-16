package wsg.lol.common.enums.match;

import wsg.lol.common.pojo.serialize.JSONSerializable;

/**
 * Enum for id of teams in the match.
 *
 * @author Kingen
 */
public enum TeamIdEnum implements JSONSerializable<Integer> {
    Blue(100),
    Red(200);

    private Integer value;

    TeamIdEnum(Integer value) {
        this.value = value;
    }

    @Override
    public Integer serialize() {
        return value;
    }
}
