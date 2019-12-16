package wsg.lol.common.enums.match;

import wsg.lol.common.pojo.serialize.JSONSerializable;

/**
 * Enum for result of the team in the match.
 *
 * @author Kingen
 */
public enum TeamResultEnum implements JSONSerializable<String> {
    Fail,
    Win;

    @Override
    public String serialize() {
        return this.name();
    }
}
