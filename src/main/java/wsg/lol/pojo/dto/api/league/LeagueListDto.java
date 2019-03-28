package wsg.lol.pojo.dto.api.league;


import wsg.lol.pojo.base.ApiBean;
import wsg.lol.pojo.enums.impl.code.RankQueueEnum;
import wsg.lol.pojo.enums.impl.code.TierEnum;

import java.util.List;

public class LeagueListDto extends ApiBean {

    private String leagueId;
    private String name;
    private TierEnum tier;
    private RankQueueEnum queue;

    private List<LeagueItemDto> itemDmoList;

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

    public List<LeagueItemDto> getItemDmoList() {
        return itemDmoList;
    }

    public void setItemDmoList(List<LeagueItemDto> itemDmoList) {
        this.itemDmoList = itemDmoList;
    }
}
