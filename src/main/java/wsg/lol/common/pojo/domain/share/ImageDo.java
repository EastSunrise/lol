package wsg.lol.common.pojo.domain.share;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDo;
import wsg.lol.common.enums.share.ImageGroupEnum;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DO for the image.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(schema = "lol", name = "t_image")
public class ImageDo extends BaseDo {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column
    private Integer relatedId;

    @Column(name = "`group`")
    private ImageGroupEnum group;

    @Column
    private String full;

    @Column
    private String sprite;

    @Column
    private Integer x;
    @Column
    private Integer y;
    @Column
    private Integer w;
    @Column
    private Integer h;
}