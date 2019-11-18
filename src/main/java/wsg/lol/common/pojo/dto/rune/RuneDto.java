package wsg.lol.common.pojo.dto.rune;

/**
 * wsg
 *
 * @author EastSunrise
 */
public class RuneDto {

    private Integer treeId;
    private Integer numX;
    private Integer numY;

    private Integer id;
    private String key;
    private String icon;
    private String name;
    private String shortDesc;
    private String longDesc;

    public Integer getTreeId() {
        return treeId;
    }

    public void setTreeId(Integer treeId) {
        this.treeId = treeId;
    }

    public Integer getNumX() {
        return numX;
    }

    public void setNumX(Integer numX) {
        this.numX = numX;
    }

    public Integer getNumY() {
        return numY;
    }

    public void setNumY(Integer numY) {
        this.numY = numY;
    }

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
        this.key = key;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }
}
