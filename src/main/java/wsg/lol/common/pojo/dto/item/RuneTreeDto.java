package wsg.lol.common.pojo.dto.item;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

/**
 * DTO for rune trees.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RuneTreeDto extends BaseDto {

    private Integer id;

    private String key;

    private String icon;

    private String name;
}
