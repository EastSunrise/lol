package wsg.lol.common.pojo.dto.champion;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.champion.ChampionTipEnum;

/**
 * DTO for the tip for the champion.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ChampionTipDto extends BaseDto {

    private Integer id;

    private Integer championId;

    private String tip;

    private ChampionTipEnum type;
}