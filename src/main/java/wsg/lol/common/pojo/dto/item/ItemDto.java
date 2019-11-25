package wsg.lol.common.pojo.dto.item;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.base.IJson;
import wsg.lol.common.enums.champion.ItemTagEnum;
import wsg.lol.common.enums.game.MapEnum;
import wsg.lol.common.pojo.parser.MapsDeserializer;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.Map;

/**
 * Bean for items.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(schema = "lol", name = "i_item")
public class ItemDto extends BaseDto implements IJson {

    @Id
    private Integer id;

    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String colloq;
    @Column
    private String plaintext;
    @Column(name = "`from`")
    private List<Integer> from;
    @Column(name = "`into`")
    private List<Integer> into;
    @Column
    private Integer goldBase;
    @Column
    private Boolean goldPurchasable;
    @Column
    private Integer goldTotal;
    @Column
    private Integer goldSell;
    @Column
    private ItemTagEnum[] tags;

    @JSONField(deserializeUsing = MapsDeserializer.class)
    @Column
    private MapEnum[] maps;

    @Column
    private Integer depth;
    @Column
    private Map<String, Double> effect;

    @Column
    private Boolean consumed;
    @Column
    private Boolean consumeOnFull;
    @Column
    private Boolean inStore;
    @Column
    private Boolean hideFromAll;
    @Column
    private Integer stacks;
    @Column
    private String requiredChampion; // the key of the champion.
    @Column
    private Integer specialRecipe;
    @Column
    private String requiredAlly; // specified for Ornn.
}
