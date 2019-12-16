package wsg.lol.common.pojo.domain.item;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDo;
import wsg.lol.common.enums.match.GameModeEnum;
import wsg.lol.common.enums.share.MapEnum;
import wsg.lol.common.enums.share.RecommendedTypeEnum;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DO for recommended items.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(schema = "lol", name = "ct_recommended")
public class RecommendedDo extends BaseDo {

    @Id
    private Integer id;

    @Column
    private String champion;

    @Column
    private String title;

    @Column
    private MapEnum map;

    @Column
    private GameModeEnum mode;

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