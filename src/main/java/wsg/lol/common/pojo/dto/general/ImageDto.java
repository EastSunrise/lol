package wsg.lol.common.pojo.dto.general;

import wsg.lol.common.enums.champion.ImageGroupEnum;
import wsg.lol.common.pojo.base.BaseDto;

/**
 * 图片资源
 *
 * @author EastSunrise
 */
public class ImageDto extends BaseDto {

    private Integer id;
    private Integer relatedId;

    private String full;
    private String sprite;
    private ImageGroupEnum group;
    private int x;
    private int y;
    private int w;
    private int h;

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

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public ImageGroupEnum getGroup() {
        return group;
    }

    public void setGroup(ImageGroupEnum group) {
        this.group = group;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }
}
