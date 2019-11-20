package wsg.lol.common.pojo.dmo.league;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.rank.RankQueueEnum;
import wsg.lol.common.enums.rank.TierEnum;

/**
 * Bean for leagues.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LeagueDto extends BaseDto {

    private String leagueId;
    private String name;
    private TierEnum tier;
    private RankQueueEnum queue;
}
