package wsg.lol.common.enums.shared;

import wsg.lol.common.pojo.parser.JsonSerializable;

/**
 * Enum for service.
 *
 * @author Kingen
 */
public enum ServiceEnum implements JsonSerializable {
    Game,
    Store,
    Website,
    Client;

    @Override
    public String serialize() {
        return name();
    }
}
