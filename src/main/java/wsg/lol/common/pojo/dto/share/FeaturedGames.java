package wsg.lol.common.pojo.dto.share;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

import java.util.List;

/**
 * DTO for the list of feature games.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FeaturedGames extends BaseDto {

    /**
     * The suggested interval to wait before requesting FeaturedGames again
     */
    private Long clientRefreshInterval;

    /**
     * The list of featured games.
     */
    private List<FeaturedGameDto> gameList;
}
