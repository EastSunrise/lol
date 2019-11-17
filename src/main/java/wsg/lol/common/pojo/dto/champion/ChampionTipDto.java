package wsg.lol.common.pojo.dto.champion;

import wsg.lol.common.enums.champion.ChampionTipEnum;

public class ChampionTipDto {

    private Integer id;

    private String championId;

    private String tip;

    private ChampionTipEnum type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChampionId() {
        return championId;
    }

    public void setChampionId(String championId) {
        this.championId = championId == null ? null : championId.trim();
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip == null ? null : tip.trim();
    }

    public ChampionTipEnum getType() {
        return type;
    }

    public void setType(ChampionTipEnum type) {
        this.type = type;
    }
}