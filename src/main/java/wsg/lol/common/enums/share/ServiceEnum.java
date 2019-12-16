package wsg.lol.common.enums.share;

import wsg.lol.common.pojo.serialize.JSONSerializable;

/**
 * Enum for service.
 *
 * @author Kingen
 */
public enum ServiceEnum implements JSONSerializable<String> {
    Game,
    Store,
    Website,
    Client;

    @Override
    public String serialize() {
        return name();
    }
}
