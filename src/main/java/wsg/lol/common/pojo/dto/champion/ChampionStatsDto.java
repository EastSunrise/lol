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
class ChampionStatsDto extends BaseDto {

    private Double hp;

    private Double hpperlevel;

    private Double hpregen;

    private Double hpregenperlevel;

    private Double mp;

    private Double mpperlevel;

    private Double mpregen;

    private Double mpregenperlevel;

    private Double attackdamage;

    private Double attackdamageperlevel;

    private Double armor;

    private Double armorperlevel;

    private Double spellblock;

    private Double spellblockperlevel;

    private Double attackspeed;

    private Double attackspeedperlevel;

    private Integer attackrange;

    private Integer movespeed;

    private Integer crit;

    private Integer critperlevel;
}
