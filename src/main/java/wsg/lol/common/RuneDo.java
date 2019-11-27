package wsg.lol.common;

public class RuneDo {
    private Integer id;

    private String key;

    private Integer treeId;

    private String name;

    private String icon;

    private String shortDesc;

    private String longDesc;

    private Integer numY;

    private Integer numX;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public Integer getTreeId() {
        return treeId;
    }

    public void setTreeId(Integer treeId) {
        this.treeId = treeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc == null ? null : shortDesc.trim();
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc == null ? null : longDesc.trim();
    }

    public Integer getNumY() {
        return numY;
    }

    public void setNumY(Integer numY) {
        this.numY = numY;
    }

    public Integer getNumX() {
        return numX;
    }

    public void setNumX(Integer numX) {
        this.numX = numX;
    }
}