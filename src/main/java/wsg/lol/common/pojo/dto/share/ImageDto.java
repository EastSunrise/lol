package wsg.lol.common.pojo.dto.share;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.champion.ImageGroupEnum;
import wsg.lol.common.pojo.parser.CustomEnumDeserializer;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Bean for the image.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(schema = "lol", name = "t_image")
public class ImageDto extends BaseDto {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    @Column
    private Integer relatedId;

    @Column
    private String full;
    @Column
    private String sprite;

    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    @Column(name = "`group`")
    private ImageGroupEnum group;

    @Column
    private Integer x;
    @Column
    private Integer y;
    @Column
    private Integer w;
    @Column
    private Integer h;
}
