package wsg.lol.common.pojo.dto.champion;

import wsg.lol.common.enums.champion.SpellNumEnum;
import wsg.lol.common.pojo.dto.general.ImageDto;

import java.util.List;

/**
 * // TODO: (Kingen, 2019/11/18)
 */
public class ChampionSpellDto {

    private String id;
    private Integer championId;
    private String name;
    private SpellNumEnum num;
    private String description;
    private Integer maxrank;
    private String tooltip;
    private List<String> leveltipLabel;
    private List<String> leveltipEffect;
    private String cooldown;
    private String cooldownBurn;
    private String cost;
    private String costBurn;
    private String range;
    private String rangeBurn;
    private String datavalues;
    private String effect;
    private String effectBurn;
    private String vars;
    private String costType;
    private String maxammo;
    private String resource;

    private ImageDto image;

    public ImageDto getImage() {
        return image;
    }

    public void setImage(ImageDto image) {
        this.image = image;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SpellNumEnum getNum() {
        return num;
    }

    public void setNum(SpellNumEnum num) {
        this.num = num;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMaxrank() {
        return maxrank;
    }

    public void setMaxrank(Integer maxrank) {
        this.maxrank = maxrank;
    }

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public List<String> getLeveltipLabel() {
        return leveltipLabel;
    }

    public void setLeveltipLabel(List<String> leveltipLabel) {
        this.leveltipLabel = leveltipLabel;
    }

    public List<String> getLeveltipEffect() {
        return leveltipEffect;
    }

    public void setLeveltipEffect(List<String> leveltipEffect) {
        this.leveltipEffect = leveltipEffect;
    }

    public String getCooldown() {
        return cooldown;
    }

    public void setCooldown(String cooldown) {
        this.cooldown = cooldown;
    }

    public String getCooldownBurn() {
        return cooldownBurn;
    }

    public void setCooldownBurn(String cooldownBurn) {
        this.cooldownBurn = cooldownBurn;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getCostBurn() {
        return costBurn;
    }

    public void setCostBurn(String costBurn) {
        this.costBurn = costBurn;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getRangeBurn() {
        return rangeBurn;
    }

    public void setRangeBurn(String rangeBurn) {
        this.rangeBurn = rangeBurn;
    }

    public String getDatavalues() {
        return datavalues;
    }

    public void setDatavalues(String datavalues) {
        this.datavalues = datavalues;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getEffectBurn() {
        return effectBurn;
    }

    public void setEffectBurn(String effectBurn) {
        this.effectBurn = effectBurn;
    }

    public String getVars() {
        return vars;
    }

    public void setVars(String vars) {
        this.vars = vars;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public String getMaxammo() {
        return maxammo;
    }

    public void setMaxammo(String maxammo) {
        this.maxammo = maxammo;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }
}