package wsg.lol.common;

public class BlockDo {
    private Integer id;

    private Integer recommendedId;

    private Byte type;

    private Byte recMath;

    private Byte recSteps;

    private Integer minSummonerLevel;

    private Integer maxSummonerLevel;

    private String showIfSummonerSpell;

    private String hideIfSummonerSpell;

    private String appendAfterSection;

    private String visibleWithAllOf;

    private String hiddenWithAnyOf;

    private String items;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRecommendedId() {
        return recommendedId;
    }

    public void setRecommendedId(Integer recommendedId) {
        this.recommendedId = recommendedId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getRecMath() {
        return recMath;
    }

    public void setRecMath(Byte recMath) {
        this.recMath = recMath;
    }

    public Byte getRecSteps() {
        return recSteps;
    }

    public void setRecSteps(Byte recSteps) {
        this.recSteps = recSteps;
    }

    public Integer getMinSummonerLevel() {
        return minSummonerLevel;
    }

    public void setMinSummonerLevel(Integer minSummonerLevel) {
        this.minSummonerLevel = minSummonerLevel;
    }

    public Integer getMaxSummonerLevel() {
        return maxSummonerLevel;
    }

    public void setMaxSummonerLevel(Integer maxSummonerLevel) {
        this.maxSummonerLevel = maxSummonerLevel;
    }

    public String getShowIfSummonerSpell() {
        return showIfSummonerSpell;
    }

    public void setShowIfSummonerSpell(String showIfSummonerSpell) {
        this.showIfSummonerSpell = showIfSummonerSpell == null ? null : showIfSummonerSpell.trim();
    }

    public String getHideIfSummonerSpell() {
        return hideIfSummonerSpell;
    }

    public void setHideIfSummonerSpell(String hideIfSummonerSpell) {
        this.hideIfSummonerSpell = hideIfSummonerSpell == null ? null : hideIfSummonerSpell.trim();
    }

    public String getAppendAfterSection() {
        return appendAfterSection;
    }

    public void setAppendAfterSection(String appendAfterSection) {
        this.appendAfterSection = appendAfterSection == null ? null : appendAfterSection.trim();
    }

    public String getVisibleWithAllOf() {
        return visibleWithAllOf;
    }

    public void setVisibleWithAllOf(String visibleWithAllOf) {
        this.visibleWithAllOf = visibleWithAllOf == null ? null : visibleWithAllOf.trim();
    }

    public String getHiddenWithAnyOf() {
        return hiddenWithAnyOf;
    }

    public void setHiddenWithAnyOf(String hiddenWithAnyOf) {
        this.hiddenWithAnyOf = hiddenWithAnyOf == null ? null : hiddenWithAnyOf.trim();
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items == null ? null : items.trim();
    }
}