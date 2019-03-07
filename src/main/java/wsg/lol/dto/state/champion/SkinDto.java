package wsg.lol.dto.state.champion;

import wsg.lol.common.base.ResultDto;

/**
 * @author King
 * @date 2019/2/14
 */
public class SkinDto extends ResultDto {

    private String id;
    private String heroId;
    private Integer num;
    private String name;
    private Boolean chromas;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeroId() {
        return heroId;
    }

    public void setHeroId(String heroId) {
        this.heroId = heroId;
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

    public Boolean isChromas() {
        return chromas;
    }

    public void setChromas(Boolean chromas) {
        this.chromas = chromas;
    }
}
