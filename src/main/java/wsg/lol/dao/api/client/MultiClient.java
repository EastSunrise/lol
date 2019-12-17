package wsg.lol.dao.api.client;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import wsg.lol.common.base.AppException;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.config.ApiConfig;
import wsg.lol.config.ApiIdentifier;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Client for multiple apis.
 *
 * @author Kingen
 */
@Configuration
class MultiClient extends ApiClient implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(MultiClient.class);

    private PriorityBlockingQueue<RGApi> apis;

    private ApiConfig apiConfig;

    /**
     * Get available and valid api token, specified by the username in the {@link ApiIdentifier} or used the earliest.
     *
     * @return null if there isn't a valid token.
     */
    @Override
    public synchronized String getToken() {
        String username = ApiIdentifier.getApi();
        if (username != null) {
            for (RGApi api : apis) {
                if (username.equals(api.username)) {
                    logger.info("Acquiring the token of the account {}.", username);
                    String token = api.acquire();
                    if (token == null) {
                        break;
                    }
                    apis.removeIf(rgApi -> username.equals(rgApi.username));
                    apis.offer(api);
                    return token;
                }
            }
            logger.error("There isn't a valid api under the account {}.", username);
            throw new AppException(ErrorCodeConst.HTTPS_ERROR);
        }

        RGApi api = apis.poll();
        if (api == null) {
            logger.error("There aren't valid apis from the queue.");
            throw new AppException(ErrorCodeConst.HTTPS_ERROR);
        }
        logger.info("Acquiring the token of the account {}.", api.username);
        String token = api.acquire();
        if (token == null) {
            logger.error("There aren't valid apis from the queue.");
            throw new AppException(ErrorCodeConst.HTTPS_ERROR);
        }
        apis.offer(api);
        return token;
    }

    @Override
    void occurForbidden(String token) {
        logger.warn("The token {} had been forbidden. Try another.", token);
        if (token == null) {
            return;
        }
        apis.removeIf(rgApi -> token.equals(rgApi.token));
    }

    public String getUsername() {
        RGApi api = apis.peek();
        if (api == null) {
            logger.error("There aren't valid tokens from the queue.");
            throw new AppException(ErrorCodeConst.HTTPS_ERROR);
        }
        return api.username;
    }

    @Override
    public String getPath() {
        return "multi";
    }

    /**
     * Initial the apis.
     */
    @Override
    public void afterPropertiesSet() {
        apis = new PriorityBlockingQueue<>(apiConfig.getTokens().size(), (api1, api2) -> {
            int compare = -Boolean.compare(api1.valid, api2.valid);
            return compare == 0 ? Long.compare(api1.nextFreeTicket, api2.nextFreeTicket) : compare;
        });
        for (ApiConfig.Token token : apiConfig.getTokens()) {
            if (StringUtils.isNotBlank(token.getKey())) {
                this.apis.add(new RGApi(token, apiConfig.getRate()));
            }
        }
    }

    @Autowired
    public void setApiConfig(ApiConfig apiConfig) {
        this.apiConfig = apiConfig;
    }
}
