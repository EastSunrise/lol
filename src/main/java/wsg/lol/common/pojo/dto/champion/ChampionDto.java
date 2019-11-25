package wsg.lol.common.pojo.dto.champion;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.champion.ChampionTagEnum;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Bean for base info of champion.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(schema = "lol", name = "c_champion")
public class ChampionDto extends BaseDto {

    @Id
    @JSONField(name = "key")
    private Integer id;

    @Column(name = "`key`")
    @JSONField(name = "id")
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
    private String partype;

    @Column
    private ChampionTagEnum[] tags;

    @Column
    private Integer infoAttack;
    @Column
    private Integer infoDefense;
    @Column
    private Integer infoMagic;
    @Column
    private Integer infoDifficulty;
}
