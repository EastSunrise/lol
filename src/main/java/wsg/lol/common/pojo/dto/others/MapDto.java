package wsg.lol.common.pojo.dto.others;

import wsg.lol.common.enums.game.MapEnum;
import wsg.lol.common.pojo.base.BaseDto;
import wsg.lol.common.pojo.base.IJson;
import wsg.lol.common.pojo.dto.general.ImageDto;

/**
 * wsg
 *
 * @author EastSunrise
 */
public class MapDto extends BaseDto implements IJson {

    private MapEnum map;
    private ImageDto image;

    public MapEnum getMap() {
        return map;
    }

    public void setMap(MapEnum map) {
        this.map = map;
    }

    public ImageDto getImage() {
        return image;
    }

    public void setImage(ImageDto image) {
        this.image = image;
    }
}
