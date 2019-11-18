package wsg.lol.common.pojo.dto.champion;

import wsg.lol.common.base.BaseDto;

/**
 * wsg
 *
 * @author EastSunrise
 */
public class SkinDto extends BaseDto {

    private Integer championId;

    private Integer id;
    private Integer num;
    private String name;
    private Boolean chromas;

    public Integer getChampionId() {
        return championId;
    }

    public void setChampionId(Integer championId) {
        this.championId = championId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getChromas() {
        return chromas;
    }

    public void setChromas(Boolean chromas) {
        this.chromas = chromas;
    }
}
