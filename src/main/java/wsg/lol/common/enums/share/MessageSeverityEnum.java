package wsg.lol.common.enums.share;

import wsg.lol.common.pojo.serialize.StringSerializable;

/**
 * Enum for the severity of message.
 *
 * @author Kingen
 */
public enum MessageSeverityEnum implements StringSerializable {
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
