package wsg.lol.common.pojo.dto.summoner;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

/**
 * Bean for miniSeries.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MiniSeriesDto extends BaseDto {

    private String progress;
    private Integer target;
    private Integer wins;
    private Integer losses;
}
