package wsg.lol.common.pojo.dto.share;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.champion.ItemTagEnum;
import wsg.lol.common.enums.game.MapEnum;
import wsg.lol.common.pojo.parser.MapsDeserializer;

import java.util.List;
import java.util.Map;

/**
 * DTO for items.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ItemDto extends BaseDto {

    private Integer id;

    private String name;

    private String description;

    private String colloq;

    private String plaintext;

    private List<Integer> from;

    private List<Integer> into;

    private Map<String, Object> gold;

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
