package wsg.lol.common.pojo.domain.champion;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDo;
import wsg.lol.common.enums.champion.ChampionTipEnum;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DO for the tip for the champion.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(schema = "lol", name = "c_tip")
public class ChampionTipDo extends BaseDo {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column
    private Integer championId;

    @Column
    private String tip;

    @Column
    private ChampionTipEnum type;
}