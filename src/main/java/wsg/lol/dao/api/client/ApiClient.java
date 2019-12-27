package wsg.lol.dao.api.client;

import com.google.common.util.concurrent.RateLimiter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import wsg.lol.common.base.AppException;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.common.enums.system.RegionEnum;
import wsg.lol.common.enums.system.ResponseCodeEnum;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Client for a single api.
 *
 * @author Kingen
 */
@Configuration
@ConfigurationProperties(prefix = "api")
@SuppressWarnings("UnstableApiUsage")
public class ApiClient implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(ApiClient.class);

    @Setter
    private Map<String, String> regions;

    @Setter
    private double rate;

    /**
     * region-username map for the account assigned for the region.
     */
    private Map<RegionEnum, String> apis;

    /**
     * username-token map of the developer accounts.
     */
    @Setter
    private Map<String, Token> tokens;

    /**
     * username-limiter map to limit to acquiring the token.
     */
    private Map<String, RateLimiter> limiters;

    /**
     * Get the token under the account assigned for the region.
     */
    public String getToken(RegionEnum region) {
        String username = apis.get(region);
        RateLimiter limiter = limiters.get(username);
        Token token = tokens.get(username);
        if (limiter == null || token == null || StringUtils.isBlank(token.key)) {
            logger.error("There isn't a valid key under the account {}.", username);
            throw new AppException(ErrorCodeConst.SYSTEM_ERROR);
        }

        limiter.acquire();
        return token.key;
    }

    /**
     * Remove the related api when the {@link ResponseCodeEnum#Forbidden} occurred.
     */
    public void occurForbidden(RegionEnum region) {
        String username = apis.get(region);
        tokens.remove(username);
        limiters.remove(username);
        apis.remove(region);
        logger.error("The api under the account {} has expired, removed.", username);
        throw new AppException(ErrorCodeConst.HTTPS_ERROR);
    }

    /**
     * Get available regions.
     */
    public Set<RegionEnum> getRegions() {
        return apis.keySet();
    }

    @Override
    public void afterPropertiesSet() {
        for (Map.Entry<String, Token> entry : tokens.entrySet()) {
            entry.getValue().username = entry.getKey();
        }

        Set<String> usernames = tokens.keySet();
        limiters = new ConcurrentHashMap<>(usernames.size());
        for (String username : usernames) {
            if (StringUtils.isNotBlank(username)) {
                limiters.put(username, RateLimiter.create(rate));
            }
        }

        apis = new ConcurrentHashMap<>(11);
        for (Map.Entry<String, String> entry : regions.entrySet()) {
            apis.put(Enum.valueOf(RegionEnum.class, entry.getKey()), entry.getValue());
        }
    }

    @Setter
    private static class Token {
        private String username;
        private String key;
    }
}
