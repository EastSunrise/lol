package wsg.lol.common.pojo.dto.champion;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

/**
 * DTO for stats of the champion.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ChampionStatsDto extends BaseDto {

    private Integer championId;

    private Integer hp;

    private Integer hpperlevel;

    private Integer mp;

    private Integer mpperlevel;

    private Integer movespeed;

    private Integer armor;

    private Double armorperlevel;

    private Double spellblock;

    private Double spellblockperlevel;

    private Integer attackrange;

    private Integer hpregen;

    private Double hpregenperlevel;

    private Integer mpregen;

    private Integer mpregenperlevel;

    private Integer crit;

    private Integer critperlevel;

    private Integer attackdamage;

    private Integer attackdamageperlevel;

    private Double attackspeedperlevel;

    private Double attackspeed;
}
