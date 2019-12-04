package wsg.lol.common.pojo.dto.league;


import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.share.RankQueueEnum;
import wsg.lol.common.enums.summoner.TierEnum;

import java.util.List;

/**
 * DTO for list of leagues.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LeagueListDto extends BaseDto {

    private String leagueId;

    private String name;

    private TierEnum tier;

    private RankQueueEnum queue;

    private List<LeagueItemDto> entries;
}
