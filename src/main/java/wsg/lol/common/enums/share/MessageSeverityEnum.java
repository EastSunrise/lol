package wsg.lol.common.enums.share;

import wsg.lol.dao.common.serialize.JSONSerializable;

/**
 * Enum for the severity of message.
 *
 * @author Kingen
 */
public enum MessageSeverityEnum implements JSONSerializable<String> {
    Info("info");

    private String description;

    MessageSeverityEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String serialize() {
        return getDescription();
    }
}
