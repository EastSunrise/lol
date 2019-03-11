package wsg.lol.common.constants;

import wsg.lol.common.enums.impl.others.RegionalProxyEnum;
import wsg.lol.common.enums.impl.others.ServiceProxyEnum;

/**
 * @author King
 * @date 2019/2/11
 */
public class DefaultConfig {
    private static ServiceProxyEnum region = ServiceProxyEnum.KR;

    private static String API_KEY = "RGAPI-5945a394-b79f-4ffc-8e35-a4281e300610";

    private static String language = "zh_CN";

    private static RegionalProxyEnum globalProxy = RegionalProxyEnum.Americas;

    private static String dataDir = "data";

    private static String latestVersion = "9.5.1";

    public static String getDataDir() {
        return dataDir;
    }

    public static void setDataDir(String dataDir) {
        DefaultConfig.dataDir = dataDir;
    }

    public static RegionalProxyEnum getGlobalProxy() {
        return globalProxy;
    }

    public static void setGlobalProxy(RegionalProxyEnum globalProxy) {
        DefaultConfig.globalProxy = globalProxy;
    }

    public static ServiceProxyEnum getRegion() {
        return region;
    }

    public static void setRegion(ServiceProxyEnum region) {
        DefaultConfig.region = region;
    }

    public static String getApiKey() {
        return API_KEY;
    }

    public static void setApiKey(String apiKey) {
        API_KEY = apiKey;
    }

    public static String getLanguage() {
        return language;
    }

    public static void setLanguage(String language) {
        DefaultConfig.language = language;
    }

    public static String getLatestVersion() {
        return latestVersion;
    }

    public static void setLatestVersion(String latestVersion) {
        DefaultConfig.latestVersion = latestVersion;
    }
}
