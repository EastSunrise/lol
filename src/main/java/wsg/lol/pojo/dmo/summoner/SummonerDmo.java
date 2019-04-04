package wsg.lol.pojo.dmo.summoner;


import wsg.lol.pojo.base.BaseDmo;
import wsg.lol.pojo.base.Persistable;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "s_summoner")
public class SummonerDmo extends BaseDmo implements Persistable {

    @Id
    private String id;

    private String accountId;
    private String puuid;
    private String name;
    private Integer profileIconId;
    private Date revisionDate;
    private Integer summonerLevel;
    private Date lastCheckedTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPuuid() {
        return puuid;
    }

    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(Integer profileIconId) {
        this.profileIconId = profileIconId;
    }

    public Date getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(Date revisionDate) {
        this.revisionDate = revisionDate;
    }

    public Integer getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(Integer summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public Date getLastCheckedTime() {
        return lastCheckedTime;
    }

    public void setLastCheckedTime(Date lastCheckedTime) {
        this.lastCheckedTime = lastCheckedTime;
    }
}
