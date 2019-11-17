package wsg.lol.common.pojo.dto.others;

import wsg.lol.common.pojo.base.BaseDto;
import wsg.lol.common.pojo.base.IJson;

/**
 * wsg
 *
 * @author EastSunrise
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
