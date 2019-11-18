package wsg.lol.common.pojo.dto.summoner;

import wsg.lol.common.base.BaseDto;
import wsg.lol.common.base.IJson;

import java.util.Date;

/**
 * // TODO: (Kingen, 2019/11/18)
 * @author EastSunrise
 */
public class SummonerDto extends BaseDto implements IJson {

    /**
     * Encrypted summoner ID. Max length 63 characters.
     */
    private String id;

    /**
     * Encrypted account ID. Max length 56 characters.
     */
    private String accountId;

    /**
     * Encrypted PUUID. Exact length of 78 characters.
     */
    private String puuid;

    /**
     * Summoner name.
     */
    private String name;

    /**
     * ID of the summoner icon associated with the summoner.
     */
    private int profileIconId;

    /**
     * Date summoner was last modified specified as epoch milliseconds. The following events will update this timestamp:
     * profile icon change, playing the tutorial or advanced tutorial, finishing a game, summoner name change
     */
    private Date revisionDate;

    /**
     * Summoner level associated with the summoner.
     */
    private long summonerLevel;

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

    public int getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(int profileIconId) {
        this.profileIconId = profileIconId;
    }

    public Date getRevisionDate() {
        return revisionDate;
    }

    /**
     * @param revisionDate
     *         时间戳
     */
    public void setRevisionDate(long revisionDate) {
        this.revisionDate = new Date(revisionDate);
    }

    public void setRevisionDate(Date revisionDate) {
        this.revisionDate = revisionDate;
    }

    public long getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(long summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public Date getLastCheckedTime() {
        return lastCheckedTime;
    }

    public void setLastCheckedTime(Date lastCheckedTime) {
        this.lastCheckedTime = lastCheckedTime;
    }
}
