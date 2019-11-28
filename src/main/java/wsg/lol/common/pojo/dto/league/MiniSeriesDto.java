package wsg.lol.common.pojo.dto.league;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

/**
 * DTO for miniSeries.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
class MiniSeriesDto extends BaseDto {

    private String progress;
    private Integer target;
    private Integer wins;
    private Integer losses;
}
