package wsg.lol.common.pojo.dto.item;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.champion.RecommendedTypeEnum;
import wsg.lol.common.enums.game.GameModeEnum;
import wsg.lol.common.enums.game.MapEnum;
import wsg.lol.common.pojo.parser.CustomEnumDeserializer;

/**
 * Bean for recommended items.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RecommendedDto extends BaseDto {

    private Integer id;

    private String champion;// champion key
    private String title;

    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    private MapEnum map;

    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    private GameModeEnum mode;

    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    private RecommendedTypeEnum type;

    private Boolean priority;
    private Object customTag;
    private Integer sortrank;
    private Boolean extensionPage;
    private Boolean useObviousCheckmark;
    private Boolean extenOrnnPage;
    private Object requiredPerk;
    private Object customPanel;
    private Object customPanelBuffCurrencyName;
    private Object customPanelCurrencyType;

    /**
     * Generate the id by <expression>{@param #index} * 10000 + {@param #championId}</expression>
     */
    public static Integer generateId(Integer championId, int index) {
        return index * 10000 + championId;
    }
}
