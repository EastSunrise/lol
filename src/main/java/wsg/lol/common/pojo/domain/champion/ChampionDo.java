package wsg.lol.common.pojo.domain.champion;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDo;
import wsg.lol.common.enums.champion.ChampionTagEnum;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DO for the champion.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(schema = "lol", name = "c_champion")
public class ChampionDo extends BaseDo {

    @Id
    private Integer id;

    @Column(name = "`key`")
    private String key;

    @Column
    private String name;

    @Column
    private String title;

    @Column
    private String lore;

    @Column
    private String blurb;

    @Column
    private ChampionTagEnum[] tags;

    @Column
    private String partype;

    @Column
    private Integer infoAttack;

    @Column
    private Integer infoDefense;

    @Column
    private Integer infoMagic;

    @Column
    private Integer infoDifficulty;
}