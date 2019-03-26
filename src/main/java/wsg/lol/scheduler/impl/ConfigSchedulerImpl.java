package wsg.lol.scheduler.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import wsg.lol.common.utils.DateUtil;
import wsg.lol.common.utils.FileUtil;
import wsg.lol.dao.config.ApiKey;
import wsg.lol.dao.config.Config;
import wsg.lol.scheduler.intf.ConfigScheduler;

import java.util.Locale;

/**
 * wsg
 *
 * @author wangsigen
 */
@Component
public class ConfigSchedulerImpl implements ConfigScheduler {

    private Config config;

    @Scheduled(fixedRate = DateUtil.ONE_MINUTE)
    public void readApiKey() {
        JSONObject apiObject = FileUtil.readJSONObject(config.getApiKeyPath());
        ApiKey.setApiKey(apiObject.getString("apiKey"));
        // Pacific Time Zone to local time.
        ApiKey.setExpiredTime(DateUtil.getDate(apiObject.getString("expiredTime"), DateUtil.API_EXPIRED_FORMAT, "PST",
                Locale.ENGLISH));
        ApiKey.setCurrentVersion(apiObject.getString("currentVersion"));
    }

    @Scheduled(fixedRate = DateUtil.ONE_MINUTE * 3)
    public void supplySummoners() {
    }

    @Autowired
    public void setConfig(Config config) {
        this.config = config;
    }
}
