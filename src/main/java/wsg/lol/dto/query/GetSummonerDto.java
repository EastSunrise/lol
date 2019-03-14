package wsg.lol.dto.query;

import wsg.lol.common.base.QueryDto;

/**
 * @author King
 * @date 2019/3/9
 */
public class GetSummonerDto extends QueryDto {

    private String id;

    private String accountId;

    private String puuid;

    private String name;

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
}
