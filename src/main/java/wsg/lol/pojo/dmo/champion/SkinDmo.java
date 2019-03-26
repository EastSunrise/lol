package wsg.lol.pojo.dmo.champion;


import wsg.lol.pojo.base.ApiBean;

public class SkinDmo extends ApiBean {

    private String id;
    private Integer championId;
    private Integer num;
    private String name;
    private Boolean chromas;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Integer getChampionId() {
        return championId;
    }

    public void setChampionId(Integer championId) {
        this.championId = championId;
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
