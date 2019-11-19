package wsg.lol.common.pojo.dto.recommend;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

/**
 * // TODO: (Kingen, 2019/11/19) *
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ItemRefDto extends BaseDto {

    private Integer id;
    private Integer count;
    private Boolean hideCount;
}
