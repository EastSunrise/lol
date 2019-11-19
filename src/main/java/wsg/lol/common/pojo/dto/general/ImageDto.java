package wsg.lol.common.pojo.dto.general;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.champion.ImageGroupEnum;

/**
 * Bean for the image.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ImageDto extends BaseDto {

    private Integer id;
    private Integer relatedId;

    private String full;
    private String sprite;
    private ImageGroupEnum group;
    private Integer x;
    private Integer y;
    private Integer w;
    private Integer h;
}
