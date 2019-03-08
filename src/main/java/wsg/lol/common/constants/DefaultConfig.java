package wsg.lol.common.constants;

import wsg.lol.common.enums.impl.others.RegionalProxyEnum;
import wsg.lol.common.enums.impl.others.ServiceProxyEnum;

/**
 * @author King
 * @date 2019/2/11
 */
public class DefaultConfig {
    private static ServiceProxyEnum region = ServiceProxyEnum.KR;

    private static String API_KEY = "RGAPI-bc627a29-a9f1-4eb6-b5e8-08ca87476acf";

    private static String language = "zh_CN";

    private static RegionalProxyEnum globalProxy = RegionalProxyEnum.Americas;

    private static String dataDir = "lol";

    private static String latestVersion = "9.4.1";

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
