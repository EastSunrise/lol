package wsg.lol.dao.api.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import wsg.lol.common.base.AppException;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.config.ApiConfig;

/**
 * Client for a single api.
 *
 * @author Kingen
 */
@Configuration
@Primary
class SingleClient extends ApiClient implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(SingleClient.class);

    private ApiConfig apiConfig;

    private RGApi api;

    @Override
    public String getToken() {
        logger.info("Acquiring the token.");
        String token = api.acquire();
        if (token == null) {
            logger.error("The api has expired.");
            throw new AppException(ErrorCodeConst.HTTPS_ERROR);
        }
        return token;
    }

    @Override
    void occurForbidden(String token) {
        logger.error("The api has expired.");
        throw new AppException(ErrorCodeConst.HTTPS_ERROR);
    }

    @Override
    public String getUsername() {
        return api.username;
    }

    @Override
    public String getPath() {
        return "single";
    }

    @Override
    public void afterPropertiesSet() {
        api = new RGApi(apiConfig.getSingle(), apiConfig.getRate());
    }

    @Autowired
    public void setApiConfig(ApiConfig apiConfig) {
        this.apiConfig = apiConfig;
    }
}
