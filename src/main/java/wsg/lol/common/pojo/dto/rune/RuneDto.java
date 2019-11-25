package wsg.lol.common.pojo.dto.rune;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Bean for runes.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(schema = "lol", name = "r_rune")
public class RuneDto extends BaseDto {

    @Column
    private Integer treeId;
    @Column
    private Integer numX;
    @Column
    private Integer numY;

    @Id
    private Integer id;
    @Column(name = "`key`")
    private String key;
    @Column
    private String icon;
    @Column
    private String name;
    @Column
    private String shortDesc;
    @Column
    private String longDesc;
}
