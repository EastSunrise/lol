package wsg.lol.dto.state.others;

import wsg.lol.common.enums.impl.others.ServiceProxyEnum;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-02-28 17:14
 */
public class UpdateConfigDto {

    private String language;

    private ServiceProxyEnum region;

    private String API_KEY;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public ServiceProxyEnum getRegion() {
        return region;
    }

    public void setRegion(ServiceProxyEnum region) {
        this.region = region;
    }

    public String getAPI_KEY() {
        return API_KEY;
    }

    public void setAPI_KEY(String API_KEY) {
        this.API_KEY = API_KEY;
    }
}
