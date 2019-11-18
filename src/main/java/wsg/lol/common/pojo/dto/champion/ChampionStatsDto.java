package wsg.lol.common.pojo.dto.champion;

import wsg.lol.common.base.BaseDto;

/**
 * wsg
 *
 * @author EastSunrise
 */
public class ChampionStatsDto extends BaseDto {

    private Integer championId;

    private Integer hp;
    private Integer hpperlevel;
    private Integer mp;
    private Integer mpperlevel;
    private Integer movespeed;
    private Integer armor;
    private Double armorperlevel;
    private Double spellblock;
    private Double spellblockperlevel;
    private Integer attackrange;
    private Integer hpregen;
    private Double hpregenperlevel;
    private Integer mpregen;
    private Integer mpregenperlevel;
    private Integer crit;
    private Integer critperlevel;
    private Integer attackdamage;
    private Integer attackdamageperlevel;
    private Double attackspeedperlevel;
    private Double attackspeed;

    public Integer getChampionId() {
        return championId;
    }

    public void setChampionId(Integer championId) {
        this.championId = championId;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getHpperlevel() {
        return hpperlevel;
    }

    public void setHpperlevel(Integer hpperlevel) {
        this.hpperlevel = hpperlevel;
    }

    public Integer getMp() {
        return mp;
    }

    public void setMp(Integer mp) {
        this.mp = mp;
    }

    public Integer getMpperlevel() {
        return mpperlevel;
    }

    public void setMpperlevel(Integer mpperlevel) {
        this.mpperlevel = mpperlevel;
    }

    public Integer getMovespeed() {
        return movespeed;
    }

    public void setMovespeed(Integer movespeed) {
        this.movespeed = movespeed;
    }

    public Integer getArmor() {
        return armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
    }

    public Double getArmorperlevel() {
        return armorperlevel;
    }

    public void setArmorperlevel(Double armorperlevel) {
        this.armorperlevel = armorperlevel;
    }

    public Double getSpellblock() {
        return spellblock;
    }

    public void setSpellblock(Double spellblock) {
        this.spellblock = spellblock;
    }

    public Double getSpellblockperlevel() {
        return spellblockperlevel;
    }

    public void setSpellblockperlevel(Double spellblockperlevel) {
        this.spellblockperlevel = spellblockperlevel;
    }

    public Integer getAttackrange() {
        return attackrange;
    }

    public void setAttackrange(Integer attackrange) {
        this.attackrange = attackrange;
    }

    public Integer getHpregen() {
        return hpregen;
    }

    public void setHpregen(Integer hpregen) {
        this.hpregen = hpregen;
    }

    public Double getHpregenperlevel() {
        return hpregenperlevel;
    }

    public void setHpregenperlevel(Double hpregenperlevel) {
        this.hpregenperlevel = hpregenperlevel;
    }

    public Integer getMpregen() {
        return mpregen;
    }

    public void setMpregen(Integer mpregen) {
        this.mpregen = mpregen;
    }

    public Integer getMpregenperlevel() {
        return mpregenperlevel;
    }

    public void setMpregenperlevel(Integer mpregenperlevel) {
        this.mpregenperlevel = mpregenperlevel;
    }

    public Integer getCrit() {
        return crit;
    }

    public void setCrit(Integer crit) {
        this.crit = crit;
    }

    public Integer getCritperlevel() {
        return critperlevel;
    }

    public void setCritperlevel(Integer critperlevel) {
        this.critperlevel = critperlevel;
    }

    public Integer getAttackdamage() {
        return attackdamage;
    }

    public void setAttackdamage(Integer attackdamage) {
        this.attackdamage = attackdamage;
    }

    public Integer getAttackdamageperlevel() {
        return attackdamageperlevel;
    }

    public void setAttackdamageperlevel(Integer attackdamageperlevel) {
        this.attackdamageperlevel = attackdamageperlevel;
    }

    public Double getAttackspeedperlevel() {
        return attackspeedperlevel;
    }

    public void setAttackspeedperlevel(Double attackspeedperlevel) {
        this.attackspeedperlevel = attackspeedperlevel;
    }

    public Double getAttackspeed() {
        return attackspeed;
    }

    public void setAttackspeed(Double attackspeed) {
        this.attackspeed = attackspeed;
    }
}
