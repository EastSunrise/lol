package wsg.lol.common.pojo.dto.share;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.match.GameModeEnum;
import wsg.lol.common.enums.share.MapEnum;
import wsg.lol.common.enums.share.RecommendedTypeEnum;
import wsg.lol.common.pojo.serialize.CustomEnumDeserializer;

/**
 * DTO for recommended items.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RecommendedDto extends BaseDto {

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
}
