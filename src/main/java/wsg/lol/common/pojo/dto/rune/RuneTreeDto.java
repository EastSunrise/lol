package wsg.lol.common.pojo.dto.rune;

import wsg.lol.common.base.BaseDto;
import wsg.lol.common.base.IJson;

/**
 * todo
 *
 * @author EastSunrise
 */
public class RuneTreeDto extends BaseDto implements IJson {

    private Integer id;
    private String key;
    private String icon;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
