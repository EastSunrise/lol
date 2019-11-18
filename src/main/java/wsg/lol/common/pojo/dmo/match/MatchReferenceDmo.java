package wsg.lol.common.pojo.dmo.match;

import tk.mybatis.mapper.annotation.KeySql;
import wsg.lol.common.base.BaseDmo;
import wsg.lol.common.base.Persistable;
import wsg.lol.common.enums.game.SeasonEnum;
import wsg.lol.common.enums.rank.MatchLaneEnum;
import wsg.lol.common.enums.rank.MatchQueueEnum;
import wsg.lol.common.enums.rank.MatchRoleEnum;
import wsg.lol.common.enums.route.PlatformEnum;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * wsg
 *
 * @author EastSunrise
 */
@Table(name = "m_reference")
public class MatchReferenceDmo extends BaseDmo implements Persistable {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    private MatchLaneEnum lane;
    private Long gameId;
    private Integer champion;
    private PlatformEnum platformId;
    private SeasonEnum season;
    private MatchQueueEnum queue;
    private MatchRoleEnum role;
    private Date gameCreation;
    private String summonerId;
    private Boolean checked;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MatchLaneEnum getLane() {
        return lane;
    }

    public void setLane(MatchLaneEnum lane) {
        this.lane = lane;
    }

    public PlatformEnum getPlatformId() {
        return platformId;
    }

    public void setPlatformId(PlatformEnum platformId) {
        this.platformId = platformId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Integer getChampion() {
        return champion;
    }

    public void setChampion(Integer champion) {
        this.champion = champion;
    }

    public SeasonEnum getSeason() {
        return season;
    }

    public void setSeason(SeasonEnum season) {
        this.season = season;
    }

    public MatchQueueEnum getQueue() {
        return queue;
    }

    public void setQueue(MatchQueueEnum queue) {
        this.queue = queue;
    }

    public MatchRoleEnum getRole() {
        return role;
    }

    public void setRole(MatchRoleEnum role) {
        this.role = role;
    }

    public Date getGameCreation() {
        return gameCreation;
    }

    public void setGameCreation(Date gameCreation) {
        this.gameCreation = gameCreation;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
