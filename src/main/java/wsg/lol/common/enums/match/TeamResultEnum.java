package wsg.lol.common.enums.match;

import wsg.lol.common.pojo.parser.JsonSerializable;

/**
 * Enum for result of the team in the match.
 *
 * @author Kingen
 */
public enum TeamResultEnum implements JsonSerializable {
    Fail,
    Win;

    @Override
    public String serialize() {
        return this.name();
    }
}
