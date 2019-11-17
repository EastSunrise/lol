package wsg.lol.common.pojo.dto.champion;

import wsg.lol.common.pojo.base.BaseDto;

/**
 * wsg
 *
 * @author EastSunrise
 */
public class SkinDto extends BaseDto {

    private String championId;

    private Integer id;
    private int num;
    private String name;
    private boolean chromas;

    public String getChampionId() {
        return championId;
    }

    public void setChampionId(String championId) {
        this.championId = championId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChromas() {
        return chromas;
    }

    public void setChromas(boolean chromas) {
        this.chromas = chromas;
    }
}
