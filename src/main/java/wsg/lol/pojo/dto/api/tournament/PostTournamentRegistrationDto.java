package wsg.lol.pojo.dto.api.tournament;

import wsg.lol.pojo.base.QueryDto;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-01 13:59
 */
public class PostTournamentRegistrationDto extends QueryDto {

    /**
     * The provider ID to specify the regional registered provider data to associate this tournament.
     */
    private Integer providerId;

    /**
     * The optional name of the tournament.
     */
    private String name;

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
