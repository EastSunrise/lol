package wsg.lol.common.pojo.dto.state.item;

import org.springframework.data.annotation.Id;
import wsg.lol.common.pojo.base.BaseDto;
import wsg.lol.common.pojo.base.IJson;

/**
 * wsg
 *
 * @author EastSunrise
 */
public class GroupDto extends BaseDto implements IJson {

    @Id
    private String id;

    private String MaxGroupOwnable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaxGroupOwnable() {
        return MaxGroupOwnable;
    }

    public void setMaxGroupOwnable(String MaxGroupOwnable) {
        this.MaxGroupOwnable = MaxGroupOwnable;
    }
}
