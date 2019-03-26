package wsg.lol.pojo.dmo.champion;


import wsg.lol.common.annotation.JsonKey;
import wsg.lol.common.annotation.Label;
import wsg.lol.pojo.base.ApiBean;

public class ChampionDmo extends ApiBean {

    private Integer id;

    private String enKey;

    @JsonKey
    private String name;

    @JsonKey
    private String title;

    @JsonKey
    private String image_full;

    @JsonKey
    private String image_sprite;

    @Label
    private String tags;

    @JsonKey
    private String lore;

    @JsonKey
    private String blurb;

    @JsonKey
    private String partype;

    @JsonKey
    private Integer info_attack;

    @JsonKey
    private Integer info_defense;

    @JsonKey
    private Integer info_magic;

    @JsonKey
    private Integer info_difficulty;

    @JsonKey
    private Double stats_hp;

    @JsonKey
    private Double stats_hpperlevel;

    @JsonKey
    private Double stats_hpregen;

    @JsonKey
    private Double stats_hpregenperlevel;

    @JsonKey
    private Double stats_mp;

    @JsonKey
    private Double stats_mpperlevel;

    @JsonKey
    private Double stats_mpregen;

    @JsonKey
    private Double stats_mpregenperlevel;

    @JsonKey
    private Double stats_attackdamage;

    @JsonKey
    private Double stats_attackdamageperlevel;

    @JsonKey
    private Double stats_armor;

    @JsonKey
    private Double stats_armorperlevel;

    @JsonKey
    private Double stats_spellblock;

    @JsonKey
    private Double stats_spellblockperlevel;

    @JsonKey
    private Double stats_attackspeed;

    @JsonKey
    private Double stats_attackspeedperlevel;

    @JsonKey
    private Integer stats_attackrange;

    @JsonKey
    private Integer stats_movespeed;

    @JsonKey
    private Integer stats_crit;

    @JsonKey
    private Integer stats_critperlevel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnKey() {
        return enKey;
    }

    public void setEnKey(String enKey) {
        this.enKey = enKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public String getPartype() {
        return partype;
    }

    public void setPartype(String partype) {
        this.partype = partype;
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

    public Integer getInfo_attack() {
        return info_attack;
    }

    public void setInfo_attack(Integer info_attack) {
        this.info_attack = info_attack;
    }

    public Integer getInfo_defense() {
        return info_defense;
    }

    public void setInfo_defense(Integer info_defense) {
        this.info_defense = info_defense;
    }

    public Integer getInfo_magic() {
        return info_magic;
    }

    public void setInfo_magic(Integer info_magic) {
        this.info_magic = info_magic;
    }

    public Integer getInfo_difficulty() {
        return info_difficulty;
    }

    public void setInfo_difficulty(Integer info_difficulty) {
        this.info_difficulty = info_difficulty;
    }

    public Double getStats_hp() {
        return stats_hp;
    }

    public void setStats_hp(Double stats_hp) {
        this.stats_hp = stats_hp;
    }

    public Double getStats_hpperlevel() {
        return stats_hpperlevel;
    }

    public void setStats_hpperlevel(Double stats_hpperlevel) {
        this.stats_hpperlevel = stats_hpperlevel;
    }

    public Double getStats_hpregen() {
        return stats_hpregen;
    }

    public void setStats_hpregen(Double stats_hpregen) {
        this.stats_hpregen = stats_hpregen;
    }

    public Double getStats_hpregenperlevel() {
        return stats_hpregenperlevel;
    }

    public void setStats_hpregenperlevel(Double stats_hpregenperlevel) {
        this.stats_hpregenperlevel = stats_hpregenperlevel;
    }

    public Double getStats_mp() {
        return stats_mp;
    }

    public void setStats_mp(Double stats_mp) {
        this.stats_mp = stats_mp;
    }

    public Double getStats_mpperlevel() {
        return stats_mpperlevel;
    }

    public void setStats_mpperlevel(Double stats_mpperlevel) {
        this.stats_mpperlevel = stats_mpperlevel;
    }

    public Double getStats_mpregen() {
        return stats_mpregen;
    }

    public void setStats_mpregen(Double stats_mpregen) {
        this.stats_mpregen = stats_mpregen;
    }

    public Double getStats_mpregenperlevel() {
        return stats_mpregenperlevel;
    }

    public void setStats_mpregenperlevel(Double stats_mpregenperlevel) {
        this.stats_mpregenperlevel = stats_mpregenperlevel;
    }

    public Double getStats_attackdamage() {
        return stats_attackdamage;
    }

    public void setStats_attackdamage(Double stats_attackdamage) {
        this.stats_attackdamage = stats_attackdamage;
    }

    public Double getStats_attackdamageperlevel() {
        return stats_attackdamageperlevel;
    }

    public void setStats_attackdamageperlevel(Double stats_attackdamageperlevel) {
        this.stats_attackdamageperlevel = stats_attackdamageperlevel;
    }

    public Double getStats_armor() {
        return stats_armor;
    }

    public void setStats_armor(Double stats_armor) {
        this.stats_armor = stats_armor;
    }

    public Double getStats_armorperlevel() {
        return stats_armorperlevel;
    }

    public void setStats_armorperlevel(Double stats_armorperlevel) {
        this.stats_armorperlevel = stats_armorperlevel;
    }

    public Double getStats_spellblock() {
        return stats_spellblock;
    }

    public void setStats_spellblock(Double stats_spellblock) {
        this.stats_spellblock = stats_spellblock;
    }

    public Double getStats_spellblockperlevel() {
        return stats_spellblockperlevel;
    }

    public void setStats_spellblockperlevel(Double stats_spellblockperlevel) {
        this.stats_spellblockperlevel = stats_spellblockperlevel;
    }

    public Double getStats_attackspeed() {
        return stats_attackspeed;
    }

    public void setStats_attackspeed(Double stats_attackspeed) {
        this.stats_attackspeed = stats_attackspeed;
    }

    public Double getStats_attackspeedperlevel() {
        return stats_attackspeedperlevel;
    }

    public void setStats_attackspeedperlevel(Double stats_attackspeedperlevel) {
        this.stats_attackspeedperlevel = stats_attackspeedperlevel;
    }

    public Integer getStats_attackrange() {
        return stats_attackrange;
    }

    public void setStats_attackrange(Integer stats_attackrange) {
        this.stats_attackrange = stats_attackrange;
    }

    public Integer getStats_movespeed() {
        return stats_movespeed;
    }

    public void setStats_movespeed(Integer stats_movespeed) {
        this.stats_movespeed = stats_movespeed;
    }

    public Integer getStats_crit() {
        return stats_crit;
    }

    public void setStats_crit(Integer stats_crit) {
        this.stats_crit = stats_crit;
    }

    public Integer getStats_critperlevel() {
        return stats_critperlevel;
    }

    public void setStats_critperlevel(Integer stats_critperlevel) {
        this.stats_critperlevel = stats_critperlevel;
    }
}
