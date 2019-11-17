package wsg.lol.common.pojo.dto.item;

import com.alibaba.fastjson.annotation.JSONField;
import wsg.lol.common.enums.champion.ItemTagEnum;
import wsg.lol.common.enums.game.MapEnum;
import wsg.lol.common.pojo.base.BaseDto;
import wsg.lol.common.pojo.base.IJson;
import wsg.lol.common.pojo.deserializer.MapsDeserializer;

import java.util.List;
import java.util.Map;

/**
 * wsg
 *
 * @author EastSunrise
 */
public class ItemDto extends BaseDto implements IJson {

    private int id;

    private String name;
    private String description;
    private String colloq;
    private String plaintext;
    private List<Integer> from;
    private List<Integer> into;
    private int goldBase;
    private boolean goldPurchasable;
    private int goldTotal;
    private int goldSell;
    private ItemTagEnum[] tags;

    @JSONField(deserializeUsing = MapsDeserializer.class)
    private MapEnum[] maps;
    private int depth;
    private Map<String, Double> effect;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<Integer> getFrom() {
        return from;
    }

    public void setFrom(List<Integer> from) {
        this.from = from;
    }

    public List<Integer> getInto() {
        return into;
    }

    public void setInto(List<Integer> into) {
        this.into = into;
    }

    public int getGoldBase() {
        return goldBase;
    }

    public void setGoldBase(int goldBase) {
        this.goldBase = goldBase;
    }

    public boolean isGoldPurchasable() {
        return goldPurchasable;
    }

    public void setGoldPurchasable(boolean goldPurchasable) {
        this.goldPurchasable = goldPurchasable;
    }

    public int getGoldTotal() {
        return goldTotal;
    }

    public void setGoldTotal(int goldTotal) {
        this.goldTotal = goldTotal;
    }

    public int getGoldSell() {
        return goldSell;
    }

    public void setGoldSell(int goldSell) {
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

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public Map<String, Double> getEffect() {
        return effect;
    }

    public void setEffect(Map<String, Double> effect) {
        this.effect = effect;
    }
}
