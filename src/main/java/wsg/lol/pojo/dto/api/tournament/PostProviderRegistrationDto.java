package wsg.lol.pojo.dto.api.tournament;

import wsg.lol.pojo.base.QueryDto;
import wsg.lol.pojo.enums.impl.others.ServiceProxyEnum;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-01 13:56
 */
public class PostProviderRegistrationDto extends QueryDto {

    /**
     * The region in which the provider will be running tournaments. (Legal values: BR, EUNE, EUW, JP, LAN, LAS, NA,
     * OCE, PBE, RU, TR)
     */
    private ServiceProxyEnum region;
    /**
     * The provider's callback URL to which tournament game results in this region should be posted. The URL must be
     * well-formed, use the http or https protocol, and use the default port for the protocol (http URLs must use port
     * 80, https URLs must use port 443).
     */
    private String url;

    public ServiceProxyEnum getRegion() {
        return region;
    }

    public void setRegion(ServiceProxyEnum region) {
        this.region = region;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
