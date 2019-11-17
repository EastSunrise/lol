package wsg.lol.common.pojo.dto.state;

import wsg.lol.common.pojo.base.BaseDto;

import java.util.List;

/**
 * wsg
 *
 * @author EastSunrise
 */
public class RecommendedDto extends BaseDto {

    private String champion;
    private String title;
    private String map;
    private String mode;
    private String type;
    private String customTag;
    private int sortrank;
    private boolean extensionPage;
    private boolean useObviousCheckmark;
    private Object customPanel;
    private List<BlockDto> blocks;

    public String getChampion() {
        return champion;
    }

    public void setChampion(String champion) {
        this.champion = champion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCustomTag() {
        return customTag;
    }

    public void setCustomTag(String customTag) {
        this.customTag = customTag;
    }

    public int getSortrank() {
        return sortrank;
    }

    public void setSortrank(int sortrank) {
        this.sortrank = sortrank;
    }

    public boolean isExtensionPage() {
        return extensionPage;
    }

    public void setExtensionPage(boolean extensionPage) {
        this.extensionPage = extensionPage;
    }

    public boolean isUseObviousCheckmark() {
        return useObviousCheckmark;
    }

    public void setUseObviousCheckmark(boolean useObviousCheckmark) {
        this.useObviousCheckmark = useObviousCheckmark;
    }

    public Object getCustomPanel() {
        return customPanel;
    }

    public void setCustomPanel(Object customPanel) {
        this.customPanel = customPanel;
    }

    public List<BlockDto> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<BlockDto> blocks) {
        this.blocks = blocks;
    }

    /**
     * wsg
     *
     * @author EastSunrise
     */
    public static class BlockDto extends BaseDto {

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
        private List<BlockDto.ItemAttr> items;

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

        public List<BlockDto.ItemAttr> getItems() {
            return items;
        }

        public void setItems(List<BlockDto.ItemAttr> items) {
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
}
