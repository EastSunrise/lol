package wsg.lol.common.pojo.dto.item;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.match.GameModeEnum;
import wsg.lol.common.enums.share.MapEnum;
import wsg.lol.common.enums.share.RecommendedTypeEnum;

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

    private MapEnum map;

    private GameModeEnum mode;

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
