package wsg.lol.common.pojo.dto.share;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

/**
 * DTO for runes.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
class RuneDto extends BaseDto {

    private Integer id;

    private String key;

    private String icon;

    private String name;

    private String shortDesc;

    private String longDesc;
}
