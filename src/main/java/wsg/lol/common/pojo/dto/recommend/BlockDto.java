package wsg.lol.common.pojo.dto.recommend;

import wsg.lol.common.base.BaseDto;

import java.util.List;

/**
 * wsg
 *
 * @author EastSunrise
 */
public class BlockDto extends BaseDto {

    private String type;
    private boolean recMath;
    private boolean recSteps;
    private int minSummonerLevel;
    private int maxSummonerLevel;
    private String showIfSummonerSpell;
    private String hideIfSummonerSpell;
    private String appendAfterSection;
    private List<String> visibleWithAllOf;
    private List<String> hiddenWithAnyOf;
    private List<ItemAttr> items;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRecMath() {
        return recMath;
    }

    public void setRecMath(boolean recMath) {
        this.recMath = recMath;
    }

    public boolean isRecSteps() {
        return recSteps;
    }

    public void setRecSteps(boolean recSteps) {
        this.recSteps = recSteps;
    }

    public int getMinSummonerLevel() {
        return minSummonerLevel;
    }

    public void setMinSummonerLevel(int minSummonerLevel) {
        this.minSummonerLevel = minSummonerLevel;
    }

    public int getMaxSummonerLevel() {
        return maxSummonerLevel;
    }

    public void setMaxSummonerLevel(int maxSummonerLevel) {
        this.maxSummonerLevel = maxSummonerLevel;
    }

    public String getShowIfSummonerSpell() {
        return showIfSummonerSpell;
    }

    public void setShowIfSummonerSpell(String showIfSummonerSpell) {
        this.showIfSummonerSpell = showIfSummonerSpell;
    }

    public String getHideIfSummonerSpell() {
        return hideIfSummonerSpell;
    }

    public void setHideIfSummonerSpell(String hideIfSummonerSpell) {
        this.hideIfSummonerSpell = hideIfSummonerSpell;
    }

    public String getAppendAfterSection() {
        return appendAfterSection;
    }

    public void setAppendAfterSection(String appendAfterSection) {
        this.appendAfterSection = appendAfterSection;
    }

    public List<String> getVisibleWithAllOf() {
        return visibleWithAllOf;
    }

    public void setVisibleWithAllOf(List<String> visibleWithAllOf) {
        this.visibleWithAllOf = visibleWithAllOf;
    }

    public List<String> getHiddenWithAnyOf() {
        return hiddenWithAnyOf;
    }

    public void setHiddenWithAnyOf(List<String> hiddenWithAnyOf) {
        this.hiddenWithAnyOf = hiddenWithAnyOf;
    }

    public List<ItemAttr> getItems() {
        return items;
    }

    public void setItems(List<ItemAttr> items) {
        this.items = items;
    }

    /**
     * wsg
     *
     * @author EastSunrise
     */
    public static class ItemAttr extends BaseDto {

        private String id;
        private int count;
        private boolean hideCount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public boolean isHideCount() {
            return hideCount;
        }

        public void setHideCount(boolean hideCount) {
            this.hideCount = hideCount;
        }
    }
}
