package wsg.lol.common.pojo.dto.champion;

import wsg.lol.common.enums.champion.ChampionTipEnum;

/**
 * // TODO: (Kingen, 2019/11/18)
 */
public class ChampionTipDto {

    private Integer id;

    private Integer championId;

    private String tip;

    private ChampionTipEnum type;

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
        this.tip = tip == null ? null : tip.trim();
    }

    public ChampionTipEnum getType() {
        return type;
    }

    public void setType(ChampionTipEnum type) {
        this.type = type;
    }
}