package wsg.lol.common.pojo.dto.rune;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Bean for rune trees.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(schema = "lol", name = "r_tree")
public class RuneTreeDto extends BaseDto {

    @Id
    private Integer id;
    @Column(name = "`key`")
    private String key;
    @Column
    private String icon;
    @Column
    private String name;
}
