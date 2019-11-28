package wsg.lol.common.enums.share;

import wsg.lol.common.pojo.serialize.StringSerializable;

/**
 * Enum for service.
 *
 * @author Kingen
 */
public enum ServiceEnum implements StringSerializable {
    Game,
    Store,
    Website,
    Client;

    @Override
    public String serialize() {
        return name();
    }
}
