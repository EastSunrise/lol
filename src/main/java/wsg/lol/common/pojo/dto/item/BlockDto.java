package wsg.lol.common.pojo.dto.item;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.champion.BlockTypeEnum;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * Bean for blocks of recommended items.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(schema = "lol", name = "ct_block")
public class BlockDto extends BaseDto {

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
    private String showIfSummonerSpell; // name of summoner spell.
    @Column
    private String hideIfSummonerSpell; // name of summoner spell.
    @Column
    private Object appendAfterSection;
    @Column
    private List<String> visibleWithAllOf;
    @Column
    private List<String> hiddenWithAnyOf;
    @Column
    private List<ItemRefDto> items;

    @EqualsAndHashCode(callSuper = true)
    @Data
    private static class ItemRefDto extends BaseDto {

        private Integer id;
        private Integer count;
        private Boolean hideCount;
    }
}
