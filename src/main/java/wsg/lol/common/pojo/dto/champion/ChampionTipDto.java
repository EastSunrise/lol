package wsg.lol.common.pojo.dto.champion;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.champion.ChampionTipEnum;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Bean for tips of champions.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(schema = "lol", name = "c_tip")
public class ChampionTipDto extends BaseDto {

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