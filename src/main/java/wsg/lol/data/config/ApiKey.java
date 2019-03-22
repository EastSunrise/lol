package wsg.lol.data.config;

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

    public static boolean hasValidKey() {
        return apiKey != null && expiredTime.after(new Date());
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
}
