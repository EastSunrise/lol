package wsg.lol.common.pojo.dto.share;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.champion.ImageGroupEnum;
import wsg.lol.common.pojo.parser.CustomEnumDeserializer;

/**
 * DTO for the image.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ImageDto extends BaseDto {

    private Integer relatedId;

    private String full;

    private String sprite;

    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    private ImageGroupEnum group;

    private Integer x;
    private Integer y;
    private Integer w;
    private Integer h;
}
