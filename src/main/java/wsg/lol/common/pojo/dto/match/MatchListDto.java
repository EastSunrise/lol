package wsg.lol.common.pojo.dto.match;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

import java.util.List;

/**
 * DTO for list of matches of the summoner.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MatchListDto extends BaseDto {

    private List<MatchReferenceDto> matches;
    private Integer totalGames;
    private Integer startIndex;
    private Integer endIndex;
}
