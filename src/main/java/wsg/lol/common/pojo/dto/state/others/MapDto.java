package wsg.lol.common.pojo.dto.state.others;

import org.springframework.data.annotation.Id;
import wsg.lol.common.pojo.base.BaseDto;
import wsg.lol.common.pojo.base.IJson;

/**
 * wsg
 *
 * @author EastSunrise
 */
public class MapDto extends BaseDto implements IJson {

    @Id
    private String MapId;

    private String MapName;
    private ImageDto image;

    public String getMapName() {
        return MapName;
    }

    public void setMapName(String MapName) {
        this.MapName = MapName;
    }

    public String getMapId() {
        return MapId;
    }

    public void setMapId(String MapId) {
        this.MapId = MapId;
    }

    public ImageDto getImage() {
        return image;
    }

    public void setImage(ImageDto image) {
        this.image = image;
    }
}
