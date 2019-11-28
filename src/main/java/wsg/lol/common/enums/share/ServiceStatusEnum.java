package wsg.lol.common.enums.share;

import wsg.lol.common.pojo.serialize.StringSerializable;

/**
 * Enum for status of the service in the shared data.
 *
 * @author Kingen
 */
public enum ServiceStatusEnum implements StringSerializable {
    Online("online");

    private String description;

    ServiceStatusEnum(String description) {
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
