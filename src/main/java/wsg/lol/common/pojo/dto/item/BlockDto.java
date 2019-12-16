package wsg.lol.common.pojo.dto.item;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.share.BlockTypeEnum;

import java.util.List;

/**
 * DTO for blocks of recommended items.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BlockDto extends BaseDto {

    private BlockTypeEnum type;

    private Boolean recMath;

    private Boolean recSteps;

    private Integer minSummonerLevel;

    private Integer maxSummonerLevel;

    private String showIfSummonerSpell; // name of summoner spell.

    private String hideIfSummonerSpell; // name of summoner spell.

    private Object appendAfterSection;

    private List<String> visibleWithAllOf;

    private List<String> hiddenWithAnyOf;

    private List<ItemRefDto> items;

    @Data
    public static class ItemRefDto {
        private Integer id;
        private Integer count;
        private Boolean hideCount;
    }
}
