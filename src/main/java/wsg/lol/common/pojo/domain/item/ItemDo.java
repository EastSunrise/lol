package wsg.lol.common.pojo.domain.item;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.annotation.Flatten;
import wsg.lol.common.base.BaseDo;
import wsg.lol.common.enums.share.ItemTagEnum;
import wsg.lol.common.enums.share.MapEnum;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.Map;

/**
 * DO for items.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "i_item")
public class ItemDo extends BaseDo {

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
    @Flatten
    private Integer goldBase;

    @Column
    @Flatten
    private Boolean goldPurchasable;

    @Column
    @Flatten
    private Integer goldTotal;

    @Column
    @Flatten
    private Integer goldSell;

    @Column
    private ItemTagEnum[] tags;

    @Column
    private MapEnum[] maps;

    @Column
    private Map<String, Object> effect;

    @Column
    private Integer depth;

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
    private String requiredChampion;

    @Column
    private Integer specialRecipe;

    @Column
    private String requiredAlly;
}