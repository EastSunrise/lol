package wsg.lol.common.enums.shared;

import wsg.lol.common.pojo.parser.JsonSerializable;

/**
 * Enum for the severity of message.
 *
 * @author Kingen
 */
public enum MessageSeverityEnum implements JsonSerializable {
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
