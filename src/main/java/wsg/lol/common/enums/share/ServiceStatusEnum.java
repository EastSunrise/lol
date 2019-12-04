package wsg.lol.common.enums.share;

import wsg.lol.dao.common.serialize.JSONSerializable;

/**
 * Enum for status of the service in the shared data.
 *
 * @author Kingen
 */
public enum ServiceStatusEnum implements JSONSerializable<String> {
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
