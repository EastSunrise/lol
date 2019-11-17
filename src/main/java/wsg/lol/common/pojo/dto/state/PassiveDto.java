package wsg.lol.common.pojo.dto.state;

import wsg.lol.common.pojo.base.BaseDto;
import wsg.lol.common.pojo.dto.general.ImageDto;

/**
 * wsg
 *
 * @author EastSunrise
 */
public class PassiveDto extends BaseDto {

    private String name;
    private String description;
    private ImageDto image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageDto getImage() {
        return image;
    }

    public void setImage(ImageDto image) {
        this.image = image;
    }
}
