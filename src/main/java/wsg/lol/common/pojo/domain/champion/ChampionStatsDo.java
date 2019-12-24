package wsg.lol.common.pojo.domain.champion;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DO for stats of the champion.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "c_stats")
public class ChampionStatsDo extends BaseDo {

    @Id
    private Integer championId;

    @Column
    private Double hp;

    @Column
    private Double hpperlevel;

    @Column
    private Double hpregen;

    @Column
    private Double hpregenperlevel;

    @Column
    private Double mp;

    @Column
    private Double mpperlevel;

    @Column
    private Double mpregen;

    @Column
    private Double mpregenperlevel;

    @Column
    private Double attackdamage;

    @Column
    private Double attackdamageperlevel;

    @Column
    private Double armor;

    @Column
    private Double armorperlevel;

    @Column
    private Double spellblock;

    @Column
    private Double spellblockperlevel;

    @Column
    private Double attackspeed;

    @Column
    private Double attackspeedperlevel;

    @Column
    private Integer attackrange;

    @Column
    private Integer movespeed;

    @Column
    private Integer crit;

    @Column
    private Integer critperlevel;
}