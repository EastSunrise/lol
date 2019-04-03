package wsg.lol.pojo.dmo.match;

import org.springframework.data.annotation.Id;
import tk.mybatis.mapper.annotation.KeySql;
import wsg.lol.pojo.base.BaseDmo;
import wsg.lol.pojo.base.Persistable;
import wsg.lol.pojo.enums.impl.code.MatchLaneEnum;

import javax.persistence.Table;

/**
 * wsg
 *
 * @author wangsigen
 */
@Table(name = "m_reference")
public class MatchReferenceDmo extends BaseDmo implements Persistable {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private MatchLaneEnum lane;
    private long gameId;
    private int champion;
    private String platformId;
    private int season;
    private int queue;
    private String role;
    private long timestamp;
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

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public int getChampion() {
        return champion;
    }

    public void setChampion(int champion) {
        this.champion = champion;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getQueue() {
        return queue;
    }

    public void setQueue(int queue) {
        this.queue = queue;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
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
