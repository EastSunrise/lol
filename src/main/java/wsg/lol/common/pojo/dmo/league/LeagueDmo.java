package wsg.lol.common.pojo.dmo.league;

import wsg.lol.common.enums.rank.RankQueueEnum;
import wsg.lol.common.enums.rank.TierEnum;
import wsg.lol.common.pojo.base.BaseDmo;
import wsg.lol.common.pojo.base.Persistable;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * wsg
 *
 * @author EastSunrise
 */
@Table(name = "l_league")
public class LeagueDmo extends BaseDmo implements Persistable {

    @Id
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
