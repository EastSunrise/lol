package wsg.lol.common.pojo.dto.champion;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Bean for stats of champions.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(schema = "lol", name = "c_stats")
public class ChampionStatsDto extends BaseDto {

    @Id
    private Integer championId;

    @Column
    private Integer hp;
    @Column
    private Integer hpperlevel;
    @Column
    private Integer mp;
    @Column
    private Integer mpperlevel;
    @Column
    private Integer movespeed;
    @Column
    private Integer armor;
    @Column
    private Double armorperlevel;
    @Column
    private Double spellblock;
    @Column
    private Double spellblockperlevel;
    @Column
    private Integer attackrange;
    @Column
    private Integer hpregen;
    @Column
    private Double hpregenperlevel;
    @Column
    private Integer mpregen;
    @Column
    private Integer mpregenperlevel;
    @Column
    private Integer crit;
    @Column
    private Integer critperlevel;
    @Column
    private Integer attackdamage;
    @Column
    private Integer attackdamageperlevel;
    @Column
    private Double attackspeedperlevel;
    @Column
    private Double attackspeed;
}
