package wsg.lol.common.pojo.dto.champion;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Bean for skins.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(schema = "lol", name = "c_skin")
public class SkinDto extends BaseDto {

    @Column
    private Integer championId;

    @Id
    private Integer id;
    @Column
    private Integer num;
    @Column
    private String name;
    @Column
    private Boolean chromas;
}
