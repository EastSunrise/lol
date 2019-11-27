package wsg.lol.common;

public class ImageDo {
    private Integer id;

    private Integer relatedId;

    private Byte group;

    private String full;

    private String sprite;

    private Integer x;

    private Integer y;

    private Integer w;

    private Integer h;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRelatedId() {
        return relatedId;
    }

    public void setRelatedId(Integer relatedId) {
        this.relatedId = relatedId;
    }

    public Byte getGroup() {
        return group;
    }

    public void setGroup(Byte group) {
        this.group = group;
    }

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full == null ? null : full.trim();
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite == null ? null : sprite.trim();
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getW() {
        return w;
    }

    public void setW(Integer w) {
        this.w = w;
    }

    public Integer getH() {
        return h;
    }

    public void setH(Integer h) {
        this.h = h;
    }
}