package wsg.lol.common.pojo.dto.league;


import wsg.lol.common.base.BaseDto;
import wsg.lol.common.base.IJson;
import wsg.lol.common.enums.rank.RankQueueEnum;
import wsg.lol.common.enums.rank.TierEnum;

import java.util.List;

/**
 * // TODO: (Kingen, 2019/11/18)
 */
public class LeagueListDto extends BaseDto implements IJson {

    private String leagueId;
    private String name;
    private TierEnum tier;
    private RankQueueEnum queue;

    private List<LeagueItemDto> entries;

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TierEnum getTier() {
        return tier;
    }

    public void setTier(TierEnum tier) {
        this.tier = tier;
    }

    public RankQueueEnum getQueue() {
        return queue;
    }

    public void setQueue(RankQueueEnum queue) {
        this.queue = queue;
    }

    public List<LeagueItemDto> getEntries() {
        return entries;
    }

    public void setEntries(List<LeagueItemDto> entries) {
        this.entries = entries;
    }
}
