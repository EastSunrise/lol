package wsg.lol.service.scheduler;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import wsg.lol.common.utils.DateUtil;
import wsg.lol.common.utils.FileUtil;
import wsg.lol.data.config.ApiKey;
import wsg.lol.data.config.Config;

import java.util.Locale;

/**
 * wsg
 *
 * @author wangsigen
 */
@Component
public class Scheduler {

    @Autowired
    private Config config;

    @Scheduled(fixedRate = DateUtil.ONE_MINUTE)
    public void readApiKey() {
        JSONObject apiObject = FileUtil.readJSONObject(config.getApiKeyPath());
        ApiKey.setApiKey(apiObject.getString("apiKey"));
        // Pacific Time Zone to local time.
        ApiKey.setExpiredTime(DateUtil.getDate(apiObject.getString("expiredTime"), DateUtil.API_EXPIRED_FORMAT, "PST",
                Locale.ENGLISH));
    }

    @Scheduled(fixedRate = DateUtil.ONE_MINUTE * 3)
    public void supplySummoners() {
    }
}
