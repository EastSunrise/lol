package wsg.lol.common.enums.champion;

import wsg.lol.common.pojo.parser.JsonSerializable;

/**
 * // TODO: (Kingen, 2019/11/19) *
 *
 * @author Kingen
 */
public enum RecommendedTypeEnum implements JsonSerializable {
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
