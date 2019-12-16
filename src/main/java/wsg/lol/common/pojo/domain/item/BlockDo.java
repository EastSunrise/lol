package wsg.lol.common.pojo.domain.item;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDo;
import wsg.lol.common.enums.share.BlockTypeEnum;
import wsg.lol.common.pojo.dto.item.BlockDto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * DO for blocks of recommended items.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(schema = "lol", name = "ct_block")
public class BlockDo extends BaseDo {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column
    private Integer recommendedId;

    @Column
    private BlockTypeEnum type;

    @Column
    private Boolean recMath;

    @Column
    private Boolean recSteps;

    @Column
    private Integer minSummonerLevel;

    @Column
    private Integer maxSummonerLevel;

    @Column
    private String showIfSummonerSpell;

    @Column
    private String hideIfSummonerSpell;

    @Column
    private Object appendAfterSection;

    @Column
    private List<String> visibleWithAllOf;

    @Column
    private List<String> hiddenWithAnyOf;

    @Column
    private List<BlockDto.ItemRefDto> items;
}