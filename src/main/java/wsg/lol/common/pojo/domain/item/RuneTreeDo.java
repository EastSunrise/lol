package wsg.lol.common.pojo.domain.item;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DO for rune trees.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(schema = "lol", name = "r_tree")
public class RuneTreeDo extends BaseDo {

    @Id
    private Integer id;

    @Column(name = "`key`")
    private String key;

    @Column
    private String name;

    @Column
    private String icon;
}