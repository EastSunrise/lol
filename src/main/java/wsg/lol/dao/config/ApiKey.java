package wsg.lol.dao.config;

import wsg.lol.common.utils.LogUtil;

import java.util.Date;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-21 11:30
 */
public class ApiKey {

    private static String apiKey;

    private static Date expiredTime;

    private static String currentVersion;

    public static boolean hasValidKey() {
        if (apiKey == null) {
            LogUtil.info("There isn't api key.");
            return false;
        } else if (expiredTime.before(new Date())) {
            LogUtil.info("The api key has expired when it was " + expiredTime);
            return false;
        }
        return true;
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static void setApiKey(String apiKey) {
        ApiKey.apiKey = apiKey;
    }

    public static Date getExpiredTime() {
        return expiredTime;
    }

    public static void setExpiredTime(Date expiredTime) {
        ApiKey.expiredTime = expiredTime;
    }

    public static String getCurrentVersion() {
        return currentVersion;
    }

    public static void setCurrentVersion(String currentVersion) {
        ApiKey.currentVersion = currentVersion;
    }
}
