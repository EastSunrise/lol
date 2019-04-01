package wsg.lol.pojo.dto.state.item;

import org.springframework.data.annotation.Id;
import wsg.lol.pojo.base.BaseDto;
import wsg.lol.pojo.base.IJson;

import java.util.List;

/**
 * wsg
 *
 * @author wangsigen
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
