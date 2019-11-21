package wsg.lol.common.pojo.dto.item;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.base.IJson;
import wsg.lol.common.enums.champion.ItemTagEnum;
import wsg.lol.common.enums.game.MapEnum;
import wsg.lol.common.pojo.parser.MapsDeserializer;

import java.util.Map;

/**
 * todo
 *
 * @author EastSunrise
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ItemDto extends BaseDto implements IJson {

    private Integer id;

    private String name;
    private String description;
    private String colloq;
    private String plaintext;
    private Integer[] from;
    private Integer[] into;
    private Integer goldBase;
    private Boolean goldPurchasable;
    private Integer goldTotal;
    private Integer goldSell;
    private ItemTagEnum[] tags;

    @JSONField(deserializeUsing = MapsDeserializer.class)
    private MapEnum[] maps;

    private Integer depth;
    private Map<String, Double> effect;

    private Boolean consumed;
    private Boolean consumeOnFull;
    private Boolean inStore;
    private Boolean hideFromAll;
    private Integer stacks;
    private String requiredChampion; // the key of the champion.
    private Integer specialRecipe;
    private String requiredAlly; // specified for Ornn.
}
