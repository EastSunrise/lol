package wsg.lol.pojo.dmo.league;


import wsg.lol.pojo.base.BaseDmo;
import wsg.lol.pojo.enums.impl.code.TierEnum;
import wsg.lol.pojo.enums.impl.others.RankQueueEnum;

public class LeagueDmo extends BaseDmo {

    private String leagueId;
    private String name;
    private TierEnum tier;
    private RankQueueEnum queue;

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
}
