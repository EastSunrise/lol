package wsg.lol.pojo.dto.state.spell;

import org.springframework.data.annotation.Id;
import wsg.lol.pojo.base.BaseDto;
import wsg.lol.pojo.base.IJson;
import wsg.lol.pojo.dto.state.others.ImageDto;

import java.util.List;

/**
 * wsg
 *
 * @author wangsigen
 */
public class BaseSpellDto extends BaseDto implements IJson {

    @Id
    private String id;

    private String name;
    private String description;
    private String tooltip;
    private int maxrank;
    private String cooldownBurn;
    private String costBurn;
    private DataValueDto datavalues;
    private String costType;
    private String maxammo;
    private ImageDto image;
    private String rangeBurn;
    private String resource;
    private List<Integer> cooldown;
    private List<Integer> cost;
    private List<List<Integer>> effect;
    private List<String> effectBurn;
    private List<VarDto> vars;
    private List<Integer> range;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public int getMaxrank() {
        return maxrank;
    }

    public void setMaxrank(int maxrank) {
        this.maxrank = maxrank;
    }

    public String getCooldownBurn() {
        return cooldownBurn;
    }

    public void setCooldownBurn(String cooldownBurn) {
        this.cooldownBurn = cooldownBurn;
    }

    public String getCostBurn() {
        return costBurn;
    }

    public void setCostBurn(String costBurn) {
        this.costBurn = costBurn;
    }

    public DataValueDto getDatavalues() {
        return datavalues;
    }

    public void setDatavalues(DataValueDto datavalues) {
        this.datavalues = datavalues;
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

    public ImageDto getImage() {
        return image;
    }

    public void setImage(ImageDto image) {
        this.image = image;
    }

    public String getRangeBurn() {
        return rangeBurn;
    }

    public void setRangeBurn(String rangeBurn) {
        this.rangeBurn = rangeBurn;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public List<Integer> getCooldown() {
        return cooldown;
    }

    public void setCooldown(List<Integer> cooldown) {
        this.cooldown = cooldown;
    }

    public List<Integer> getCost() {
        return cost;
    }

    public void setCost(List<Integer> cost) {
        this.cost = cost;
    }

    public List<List<Integer>> getEffect() {
        return effect;
    }

    public void setEffect(List<List<Integer>> effect) {
        this.effect = effect;
    }

    public List<String> getEffectBurn() {
        return effectBurn;
    }

    public void setEffectBurn(List<String> effectBurn) {
        this.effectBurn = effectBurn;
    }

    public List<VarDto> getVars() {
        return vars;
    }

    public void setVars(List<VarDto> vars) {
        this.vars = vars;
    }

    public List<Integer> getRange() {
        return range;
    }

    public void setRange(List<Integer> range) {
        this.range = range;
    }

    /**
     * wsg
     *
     * @author wangsigen
     */
    public static class DataValueDto extends BaseDto {
    }

    /**
     * wsg
     *
     * @author wangsigen
     */
    public static class VarDto extends BaseDto {

        private String link;
        // wsg       private List<Double> coeff;
        private String key;

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        //        public List<Double> getCoeff() {
        //            return coeff;
        //        }
        //
        //        public void setCoeff(List<Double> coeff) {
        //            this.coeff = coeff;
        //        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
