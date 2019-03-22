package wsg.lol.pojo.dmo.champion;


import wsg.lol.common.annotation.JsonKey;
import wsg.lol.common.annotation.Label;
import wsg.lol.pojo.base.BaseDmo;
import wsg.lol.pojo.enums.impl.others.SpellTypeEnum;

public class SpellDmo extends BaseDmo {

    @JsonKey
    private String id;

    private Integer championId;

    private SpellTypeEnum spellType;

    @JsonKey
    private String name;

    /**
     * 召唤师技能
     */
    @Label
    private String modes;

    private Integer summonerLevel;

    @JsonKey
    private String description;

    /**
     * wsg
     */
    private String levelTips;

    @JsonKey
    private String tooltip;

    @JsonKey
    private String image_full;

    @JsonKey
    private String image_sprite;

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

    public SpellTypeEnum getSpellType() {
        return spellType;
    }

    public void setSpellType(SpellTypeEnum spellType) {
        this.spellType = spellType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModes() {
        return modes;
    }

    public void setModes(String modes) {
        this.modes = modes;
    }

    public Integer getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(Integer summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevelTips() {
        return levelTips;
    }

    public void setLevelTips(String levelTips) {
        this.levelTips = levelTips;
    }

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public String getImage_full() {
        return image_full;
    }

    public void setImage_full(String image_full) {
        this.image_full = image_full;
    }

    public String getImage_sprite() {
        return image_sprite;
    }

    public void setImage_sprite(String image_sprite) {
        this.image_sprite = image_sprite;
    }
}
