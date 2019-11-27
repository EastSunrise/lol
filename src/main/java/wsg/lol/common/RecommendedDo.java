package wsg.lol.common;

public class RecommendedDo {
    private Integer id;

    private String champion;

    private String title;

    private Byte map;

    private Byte mode;

    private Byte type;

    private Byte priority;

    private String customTag;

    private Integer sortrank;

    private Byte extensionPage;

    private Byte useObviousCheckmark;

    private Byte extenOrnnPage;

    private String requiredPerk;

    private String customPanel;

    private String customPanelBuffCurrencyName;

    private String customPanelCurrencyType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChampion() {
        return champion;
    }

    public void setChampion(String champion) {
        this.champion = champion == null ? null : champion.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Byte getMap() {
        return map;
    }

    public void setMap(Byte map) {
        this.map = map;
    }

    public Byte getMode() {
        return mode;
    }

    public void setMode(Byte mode) {
        this.mode = mode;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getPriority() {
        return priority;
    }

    public void setPriority(Byte priority) {
        this.priority = priority;
    }

    public String getCustomTag() {
        return customTag;
    }

    public void setCustomTag(String customTag) {
        this.customTag = customTag == null ? null : customTag.trim();
    }

    public Integer getSortrank() {
        return sortrank;
    }

    public void setSortrank(Integer sortrank) {
        this.sortrank = sortrank;
    }

    public Byte getExtensionPage() {
        return extensionPage;
    }

    public void setExtensionPage(Byte extensionPage) {
        this.extensionPage = extensionPage;
    }

    public Byte getUseObviousCheckmark() {
        return useObviousCheckmark;
    }

    public void setUseObviousCheckmark(Byte useObviousCheckmark) {
        this.useObviousCheckmark = useObviousCheckmark;
    }

    public Byte getExtenOrnnPage() {
        return extenOrnnPage;
    }

    public void setExtenOrnnPage(Byte extenOrnnPage) {
        this.extenOrnnPage = extenOrnnPage;
    }

    public String getRequiredPerk() {
        return requiredPerk;
    }

    public void setRequiredPerk(String requiredPerk) {
        this.requiredPerk = requiredPerk == null ? null : requiredPerk.trim();
    }

    public String getCustomPanel() {
        return customPanel;
    }

    public void setCustomPanel(String customPanel) {
        this.customPanel = customPanel == null ? null : customPanel.trim();
    }

    public String getCustomPanelBuffCurrencyName() {
        return customPanelBuffCurrencyName;
    }

    public void setCustomPanelBuffCurrencyName(String customPanelBuffCurrencyName) {
        this.customPanelBuffCurrencyName = customPanelBuffCurrencyName == null ? null : customPanelBuffCurrencyName.trim();
    }

    public String getCustomPanelCurrencyType() {
        return customPanelCurrencyType;
    }

    public void setCustomPanelCurrencyType(String customPanelCurrencyType) {
        this.customPanelCurrencyType = customPanelCurrencyType == null ? null : customPanelCurrencyType.trim();
    }
}