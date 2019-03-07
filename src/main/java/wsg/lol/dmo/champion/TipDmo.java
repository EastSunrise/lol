package wsg.lol.dmo.champion;


import wsg.lol.common.base.BaseDmo;

public class TipDmo extends BaseDmo {

    private Integer id;
    private Integer championId;
    private String tip;
    private Boolean type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getChampionId() {
        return championId;
    }

    public void setChampionId(Integer championId) {
        this.championId = championId;
    }


    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }
}
