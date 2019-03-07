package wsg.lol.dto.state.champion;

import wsg.lol.dto.state.others.ImageDto;

/**
 * @author King
 * @date 2019/2/14
 */
public class PassiveDto {

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
