package wsg.lol.common.pojo.dto.state.item;

import org.springframework.data.annotation.Id;
import wsg.lol.common.pojo.base.BaseDto;
import wsg.lol.common.pojo.base.IJson;

import java.util.List;

/**
 * wsg
 *
 * @author EastSunrise
 */
public class TreeDto extends BaseDto implements IJson {

    @Id
    private String header;

    private List<String> tags;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
