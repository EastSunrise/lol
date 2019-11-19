package wsg.lol.common.enums.champion;

/**
 * // TODO: (Kingen, 2019/11/19) *
 *
 * @author Kingen
 */
public enum RecommendedTypeEnum {
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
}
