package wsg.lol.pojo.dto.state.others;

import wsg.lol.pojo.base.BaseDto;
import wsg.lol.pojo.base.IJson;

/**
 * wsg
 *
 * @author wangsigen
 */
public class VersionDto extends BaseDto implements IJson {

    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
