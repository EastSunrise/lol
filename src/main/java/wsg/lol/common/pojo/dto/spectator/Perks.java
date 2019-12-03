package wsg.lol.common.pojo.dto.spectator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

import java.util.List;

/**
 * DTO for runes of the featured participant.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
class Perks extends BaseDto {

    /**
     * Primary runes path
     */
    private Long perkStyle;

    /**
     * IDs of the perks/runes assigned.
     */
    private List<Long> perkIds;

    /**
     * Secondary runes path
     */
    private Long perkSubStyle;
}
