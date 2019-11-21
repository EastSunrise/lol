package wsg.lol.common.pojo.dto.item;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.champion.BlockTypeEnum;

import java.util.List;

/**
 * Bean for blocks of recommended items.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BlockDto extends BaseDto {

    private Integer id;
    private Integer recommendedId;

    private BlockTypeEnum type;
    private Boolean recMath;
    private Boolean recSteps;
    private Integer minSummonerLevel;
    private Integer maxSummonerLevel;
    private String showIfSummonerSpell; // name of summoner spell.
    private String hideIfSummonerSpell; // name of summoner spell.
    private Object appendAfterSection;
    private String[] visibleWithAllOf;
    private String[] hiddenWithAnyOf;
    private List<ItemRefDto> items;
}
