package wsg.lol.dao.api.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import wsg.lol.common.enums.route.PlatformRoutingEnum;

import java.util.Date;

/**
 * Client for api.
 *
 * @author Kingen
 */
@Component
public class ApiClient {

    @Value("${api.username}")
    private String username;

    @Value("${api.password}")
    private String password;

    @Value("${api.region}")
    private PlatformRoutingEnum region;

    private String token;

    private Date expiresTime;

    /**
     * Get current api token. Regenerate it if invalid.
     */
    String getToken() {
        if (StringUtils.isEmpty(this.token) || this.expiresTime.compareTo(new Date()) < 0) {
            regenerateToken();
        }
        return this.token;
    }

    /**
     * Simulate logging in the account and regenerate the api token.
     */
    void regenerateToken() {
        // TODO: (Kingen, 2019/11/6) regenerate token
        this.token = "";
        this.expiresTime = new Date();
    }

    PlatformRoutingEnum getRegion() {
        return region;
    }
}
