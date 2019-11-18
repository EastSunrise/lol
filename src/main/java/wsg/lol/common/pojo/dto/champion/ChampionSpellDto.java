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
    // TODO: (Kingen, 2019/11/18)
    private List<String> leveltipLabel;
    private List<String> leveltipEffect;
    private Double[] cooldown;
    private String cooldownBurn;
    private Integer[] cost;
    private String costBurn;
    private Integer[] range;
    private String rangeBurn;
    private String datavalues;// TODO: (Kingen, 2019/11/18) all are '{}'
    private Integer[][] effect;
    private String[] effectBurn;
    private List<SpellVarDto> vars;
    private String costType;
    private Integer maxammo;
    private String resource;

    private ImageDto image;

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

    public Double[] getCooldown() {
        return cooldown;
    }

    public void setCooldown(Double[] cooldown) {
        this.cooldown = cooldown;
    }

    public String getCooldownBurn() {
        return cooldownBurn;
    }

    public void setCooldownBurn(String cooldownBurn) {
        this.cooldownBurn = cooldownBurn;
    }

    public Integer[] getCost() {
        return cost;
    }

    public void setCost(Integer[] cost) {
        this.cost = cost;
    }

    public String getCostBurn() {
        return costBurn;
    }

    public void setCostBurn(String costBurn) {
        this.costBurn = costBurn;
    }

    public Integer[] getRange() {
        return range;
    }

    public void setRange(Integer[] range) {
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

    public Integer[][] getEffect() {
        return effect;
    }

    public void setEffect(Integer[][] effect) {
        this.effect = effect;
    }

    public String[] getEffectBurn() {
        return effectBurn;
    }

    public void setEffectBurn(String[] effectBurn) {
        this.effectBurn = effectBurn;
    }

    public List<SpellVarDto> getVars() {
        return vars;
    }

    public void setVars(List<SpellVarDto> vars) {
        this.vars = vars;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public Integer getMaxammo() {
        return maxammo;
    }

    public void setMaxammo(Integer maxammo) {
        this.maxammo = maxammo;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public ImageDto getImage() {
        return image;
    }

    public void setImage(ImageDto image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }

    static class SpellVarDto {
        private String link;

        //        @JSONField(deserializeUsing = CoeffDeserializer.class)
        private List<Double> coeff;
        private String key;

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public List<Double> getCoeff() {
            return coeff;
        }

        public void setCoeff(List<Double> coeff) {
            this.coeff = coeff;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}