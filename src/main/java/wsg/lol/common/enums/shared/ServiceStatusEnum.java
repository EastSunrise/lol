package wsg.lol.common.enums.shared;

import wsg.lol.common.pojo.parser.JsonSerializable;

/**
 * // TODO: (Kingen, 2019/11/21) *
 *
 * @author Kingen
 */
public enum ServiceStatusEnum implements JsonSerializable {
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
