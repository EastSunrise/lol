package wsg.lol.common.pojo.dto.champion;

import com.alibaba.fastjson.annotation.JSONField;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.base.IJson;
import wsg.lol.common.base.Persistable;
import wsg.lol.common.enums.champion.ChampionTagEnum;

/**
 * wsg
 *
 * @author EastSunrise
 */
public class ChampionDto extends BaseDto implements IJson, Persistable {

    @JSONField(name = "key")
    private Integer id;

    @JSONField(name = "id")
    private String key;

    private String name;
    private String title;
    private String lore;
    private String blurb;
    private String partype;
    private ChampionTagEnum[] tags;

    // TODO: (Kingen, 2019/11/18)
    private Integer infoAttack;
    private Integer infoDefense;
    private Integer infoMagic;
    private Integer infoDifficulty;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public ChampionTagEnum[] getTags() {
        return tags;
    }

    public void setTags(ChampionTagEnum[] tags) {
        this.tags = tags;
    }

    public Integer getInfoAttack() {
        return infoAttack;
    }

    public void setInfoAttack(Integer infoAttack) {
        this.infoAttack = infoAttack;
    }

    public Integer getInfoDefense() {
        return infoDefense;
    }

    public void setInfoDefense(Integer infoDefense) {
        this.infoDefense = infoDefense;
    }

    public Integer getInfoMagic() {
        return infoMagic;
    }

    public void setInfoMagic(Integer infoMagic) {
        this.infoMagic = infoMagic;
    }

    public Integer getInfoDifficulty() {
        return infoDifficulty;
    }

    public void setInfoDifficulty(Integer infoDifficulty) {
        this.infoDifficulty = infoDifficulty;
    }
}
