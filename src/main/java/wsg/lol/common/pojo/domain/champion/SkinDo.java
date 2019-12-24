package wsg.lol.common.pojo.domain.champion;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DO for the skin.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "c_skin")
public class SkinDo extends BaseDo {

    @Id
    private Integer id;

    @Column
    private Integer championId;

    @Column
    private Integer num;

    @Column
    private String name;

    @Column
    private Boolean chromas;
}