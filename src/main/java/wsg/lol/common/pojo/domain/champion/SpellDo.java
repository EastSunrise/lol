package wsg.lol.common.pojo.domain.champion;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDo;
import wsg.lol.common.enums.champion.SpellNumEnum;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DO for the spells of champions or summoners.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(schema = "lol", name = "c_spell")
public class SpellDo extends BaseDo {

    @Id
    private Integer id;

    private Integer championId;

    @Column(name = "`key`")
    private String key;

    @Column
    private String name;

    @Column
    private SpellNumEnum num;

    @Column
    private String description;

    @Column
    private Integer maxrank;

    @Column
    private String tooltip;

    @Column
    private String[] leveltipLabel;

    @Column
    private String[] leveltipEffect;

    @Column
    private Double[] cooldown;

    @Column
    private String cooldownBurn;

    @Column
    private Integer[] cost;

    @Column
    private String costBurn;

    @Column(name = "`range`")
    private Integer[] range;

    @Column
    private String rangeBurn;

    @Column
    private String datavalues;

    @Column
    private Integer[][] effect;

    @Column
    private String[] effectBurn;

    @Column
    private String vars;

    @Column
    private String costType;

    @Column
    private String maxammo;

    @Column
    private String resource;

    @Column
    private Integer summonerLevel;

    @Column
    private String modes;
}