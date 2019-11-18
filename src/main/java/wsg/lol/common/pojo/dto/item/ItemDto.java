package wsg.lol.common.pojo.dto.item;

import com.alibaba.fastjson.annotation.JSONField;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.base.IJson;
import wsg.lol.common.enums.champion.ItemTagEnum;
import wsg.lol.common.enums.game.MapEnum;
import wsg.lol.common.pojo.deserializer.MapsDeserializer;

import java.util.Map;

/**
 * wsg
 *
 * @author EastSunrise
 */
public class ItemDto extends BaseDto implements IJson {

    private Integer id;

    private String name;
    private String description;
    private String colloq;
    private String plaintext;
    private Integer[] from;
    private Integer[] into;
    private Integer goldBase;
    private boolean goldPurchasable;
    private Integer goldTotal;
    private Integer goldSell;
    private ItemTagEnum[] tags;

    @JSONField(deserializeUsing = MapsDeserializer.class)
    private MapEnum[] maps;
    private Integer depth;
    private Map<String, Double> effect;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getColloq() {
        return colloq;
    }

    public void setColloq(String colloq) {
        this.colloq = colloq;
    }

    public String getPlaintext() {
        return plaintext;
    }

    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }

    public Integer[] getFrom() {
        return from;
    }

    public void setFrom(Integer[] from) {
        this.from = from;
    }

    public Integer[] getInto() {
        return into;
    }

    public void setInto(Integer[] into) {
        this.into = into;
    }

    public Integer getGoldBase() {
        return goldBase;
    }

    public void setGoldBase(Integer goldBase) {
        this.goldBase = goldBase;
    }

    public boolean isGoldPurchasable() {
        return goldPurchasable;
    }

    public void setGoldPurchasable(boolean goldPurchasable) {
        this.goldPurchasable = goldPurchasable;
    }

    public Integer getGoldTotal() {
        return goldTotal;
    }

    public void setGoldTotal(Integer goldTotal) {
        this.goldTotal = goldTotal;
    }

    public Integer getGoldSell() {
        return goldSell;
    }

    public void setGoldSell(Integer goldSell) {
        this.goldSell = goldSell;
    }

    public ItemTagEnum[] getTags() {
        return tags;
    }

    public void setTags(ItemTagEnum[] tags) {
        this.tags = tags;
    }

    public MapEnum[] getMaps() {
        return maps;
    }

    public void setMaps(MapEnum[] maps) {
        this.maps = maps;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public Map<String, Double> getEffect() {
        return effect;
    }

    public void setEffect(Map<String, Double> effect) {
        this.effect = effect;
    }
}
