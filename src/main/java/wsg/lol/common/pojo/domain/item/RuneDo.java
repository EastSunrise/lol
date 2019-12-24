package wsg.lol.common.pojo.domain.item;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DO for runes.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "r_rune")
public class RuneDo extends BaseDo {

    @Id
    private Integer id;

    @Column(name = "`key`")
    private String key;

    @Column
    private Integer treeId;

    @Column
    private String name;

    @Column
    private String icon;

    @Column
    private String shortDesc;

    @Column
    private String longDesc;

    @Column
    private Integer numY;

    @Column
    private Integer numX;
}