package wsg.lol.common.pojo.dto.general;

import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.champion.ImageGroupEnum;

/**
 * Bean for the image.
 *
 * @author Kingen
 */
public class ImageDto extends BaseDto {

    private Integer id;
    private Integer relatedId;

    private String full;
    private String sprite;
    private ImageGroupEnum group;
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
