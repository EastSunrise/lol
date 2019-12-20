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
import wsg.lol.config.RegionIdentifier;

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
class SingleClient implements ApiClient, InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(SingleClient.class);

    @Setter
    private Map<String, String> regions;

    private Map<RegionEnum, String> apis;

    @Setter
    private Map<String, Token> tokens;

    @Setter
    private double rate;

    private Map<String, RateLimiter> limiters;

    @Override
    public Token getToken() {
        String username = getUsername();
        RateLimiter limiter = limiters.get(username);
        Token token = tokens.get(username);
        if (limiter == null || token == null || StringUtils.isBlank(token.getKey())) {
            logger.error("There isn't a valid key under the account {}.", username);
            throw new AppException(ErrorCodeConst.SYSTEM_ERROR);
        }

        limiter.acquire();
        return token;
    }

    @Override
    public void occurForbidden(Token token) {
        Token remove = tokens.remove(token.getUsername());
        logger.error("The api under the account {} has expired, removed.", remove.getUsername());
        throw new AppException(ErrorCodeConst.HTTPS_ERROR);
    }

    @Override
    public String getUsername() {
        RegionEnum region = RegionIdentifier.getRegion();
        String username = apis.get(region);
        if (username == null) {
            logger.error("There is no api assigned for {}", region);
            throw new AppException(ErrorCodeConst.SYSTEM_ERROR);
        }
        return username;
    }

    @Override
    public String getPath() {
        return "single";
    }

    @Override
    public void checkEncrypt(String username) {
        if (StringUtils.compare(username, apis.get(RegionIdentifier.getRegion())) != 0) {
            logger.error("The assigned api doesn't match the one configured.");
            throw new AppException(ErrorCodeConst.SYSTEM_ERROR);
        }

    }

    @Override
    public void afterPropertiesSet() {
        for (Map.Entry<String, Token> entry : tokens.entrySet()) {
            entry.getValue().setUsername(entry.getKey());
        }

        Set<String> usernames = tokens.keySet();
        limiters = new ConcurrentHashMap<>(usernames.size());
        for (String username : usernames) {
            if (StringUtils.isNotBlank(username)) {
                limiters.put(username, RateLimiter.create(rate));
            }
        }

        apis = new ConcurrentHashMap<>(RegionEnum.values().length);
        for (Map.Entry<String, String> entry : regions.entrySet()) {
            apis.put(Enum.valueOf(RegionEnum.class, entry.getKey()), entry.getValue());
        }
    }

}
