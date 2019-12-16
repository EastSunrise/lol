package wsg.lol.common.enums.share;

import wsg.lol.common.pojo.serialize.JSONSerializable;

/**
 * Enum for types of the recommended.
 *
 * @author Kingen
 */
public enum RecommendedTypeEnum implements JSONSerializable<String> {
    Riot("riot"),
    RiotSupport("riot-support"),
    RiotMid("riot-mid"),
    ;

    private String description;

    RecommendedTypeEnum(String description) {
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
