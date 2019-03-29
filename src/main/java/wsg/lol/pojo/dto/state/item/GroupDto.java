package wsg.lol.pojo.dto.state.item;

import org.springframework.data.annotation.Id;
import wsg.lol.pojo.base.StateBean;

/**
 * wsg
 *
 * @author wangsigen
 */
public class GroupDto extends StateBean {

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