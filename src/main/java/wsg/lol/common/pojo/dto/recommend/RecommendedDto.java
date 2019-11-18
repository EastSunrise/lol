package wsg.lol.common.pojo.dto.recommend;

import wsg.lol.common.base.BaseDto;

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
    private BlockDto[] blocks;

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

    public BlockDto[] getBlocks() {
        return blocks;
    }

    public void setBlocks(BlockDto[] blocks) {
        this.blocks = blocks;
    }
}
