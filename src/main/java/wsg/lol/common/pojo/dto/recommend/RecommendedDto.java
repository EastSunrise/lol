package wsg.lol.common.pojo.dto.recommend;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.champion.RecommendedTypeEnum;
import wsg.lol.common.enums.game.GameModeEnum;
import wsg.lol.common.enums.game.MapEnum;

import java.util.List;

/**
 * wsg
 *
 * @author EastSunrise
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RecommendedDto extends BaseDto {

    private Integer id;

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
    private List<BlockDto> blocks;
}
