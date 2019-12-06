package wsg.lol.dao.api.client;

import com.google.common.util.concurrent.RateLimiter;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import wsg.lol.common.base.AppException;
import wsg.lol.common.constant.ErrorCodeConst;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/**
 * Client for api.
 *
 * @author Kingen
 */
@Configuration
public class ApiClient {

    private static final RateLimiter limiter = RateLimiter.create(0.8333);

    @Value("${api.timeout}")
    private int timeout;

    @Value("${api.username}")
    private String username;

    @Value("${api.password}")
    private String password;

    @Value("${api.token}")
    private String token;

    @Value("${api.expires}")
    private String expires;

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
     *
     * @return null if there isn't a valid token.
     */
    synchronized String getToken() {
        limiter.acquire();
        return (StringUtils.isEmpty(this.token) || parseDate(this.expires).compareTo(new Date()) < 0) && !regenerateToken() ? null : this.token;
    }

    /**
     * Simulate logging in the account and regenerate the api token.
     */
    boolean regenerateToken() {
        // todo regenerate token
        return false;
    }

    public int getTimeout() {
        return timeout;
    }
}
