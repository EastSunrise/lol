package wsg.lol.common.pojo.dto.match;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

/**
 * Bean for reference of runes.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RuneReferenceDto extends BaseDto {

    private int runeId;
    private int rank;
}
