package wsg.lol.common.pojo.dto.item;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.champion.RecommendedTypeEnum;
import wsg.lol.common.enums.game.GameModeEnum;
import wsg.lol.common.enums.game.MapEnum;
import wsg.lol.common.pojo.parser.CustomEnumDeserializer;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Bean for recommended items.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(schema = "lol", name = "ct_recommended")
public class RecommendedDto extends BaseDto {

    @Id
    private Integer id;

    @Column
    private String champion;// champion key
    @Column
    private String title;

    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    @Column
    private MapEnum map;

    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    @Column
    private GameModeEnum mode;

    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    @Column
    private RecommendedTypeEnum type;

    @Column
    private Boolean priority;
    @Column
    private Object customTag;
    @Column
    private Integer sortrank;
    @Column
    private Boolean extensionPage;
    @Column
    private Boolean useObviousCheckmark;
    @Column
    private Boolean extenOrnnPage;
    @Column
    private Object requiredPerk;
    @Column
    private Object customPanel;
    @Column
    private Object customPanelBuffCurrencyName;
    @Column
    private Object customPanelCurrencyType;

    /**
     * Generate the id by <expression>{@param #index} * 10000 + {@param #championId}</expression>
     */
    public static Integer generateId(Integer championId, int index) {
        return index * 10000 + championId;
    }
}
