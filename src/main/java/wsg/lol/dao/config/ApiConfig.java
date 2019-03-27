package wsg.lol.dao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import wsg.lol.common.utils.DateUtil;
import wsg.lol.common.utils.LogUtil;
import wsg.lol.pojo.enums.impl.others.RegionalProxyEnum;
import wsg.lol.pojo.enums.impl.others.ServiceProxyEnum;

import java.util.Date;
import java.util.Locale;

/**
 * wsg
 *
 * @author wangsigen
 */
@Component
@PropertySource(value = {"classpath:config/data-config.properties"})
public class ApiConfig {

    @Value("${api.region}")
    private ServiceProxyEnum region;

    @Value("${api.globalProxy}")
    private RegionalProxyEnum globalProxy;

    @Value("${api.apiKey}")
    private String apiKey;

    @Value("${api.expiredTime}")
    private String expiredTime;

    public boolean hasValidKey() {
        if (apiKey == null) {
            LogUtil.info("There isn't api key.");
            return false;
        } else if (getExpiredTime().before(new Date())) {
            LogUtil.info("The api key has expired when it was " + expiredTime);
            return false;
        }
        return true;
    }

    public ServiceProxyEnum getRegion() {
        return region;
    }

    public RegionalProxyEnum getGlobalProxy() {
        return globalProxy;
    }

    public String getApiKey() {
        return apiKey;
    }

    public Date getExpiredTime() {
        return DateUtil.getDate(expiredTime, DateUtil.API_EXPIRED_FORMAT, "PST", Locale.ENGLISH);
    }
}
