package wsg.lol.dao.api.client;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import wsg.lol.common.base.AppException;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.common.enums.route.PlatformRoutingEnum;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

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

    private static Date parseDate(String str) {
        String[] parts = str.split(",");
        parts[1] = parts[1].substring(0, parts[1].length() - 2);
        try {
            return DateUtils.parseDate(org.apache.commons.lang3.StringUtils.join(parts, ","), Locale.ENGLISH, "EEE, MMM dd, yyyy @ h:mmaa (Z)");
        } catch (ParseException e) {
            e.printStackTrace();
            throw new AppException(ErrorCodeConst.SYSTEM_ERROR, e);
        }
    }

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
        this.token = "RGAPI-5186675a-9991-4879-8290-a9491f7585fa";
        this.expiresTime = parseDate("Fri, Nov 22nd, 2019 @ 6:44pm (PT)");
    }

    public PlatformRoutingEnum getRegion() {
        return region;
    }
}
