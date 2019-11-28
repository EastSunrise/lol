package wsg.lol.common.enums.match;

import wsg.lol.common.pojo.serialize.StringSerializable;

/**
 * Enum for result of the team in the match.
 *
 * @author Kingen
 */
public enum TeamResultEnum implements StringSerializable {
    Fail,
    Win;

    @Override
    public String serialize() {
        return this.name();
    }
}
