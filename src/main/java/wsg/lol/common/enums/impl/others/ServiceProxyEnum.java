package wsg.lol.common.enums.impl.others;

import wsg.lol.common.enums.intf.CodeEnum;

/**
 * @author King
 * @date 2019/2/11
 */
public enum ServiceProxyEnum implements CodeEnum {
    BR("01", "BR1", "br1.api.riotgames.com"),
    EUNE("02", "EUN1", "eun1.api.riotgames.com"),
    EUW("03", "EUW1", "euw1.api.riotgames.com"),
    JP("04", "JP1", "jp1.api.riotgames.com"),
    KR("05", "KR", "kr.api.riotgames.com"),
    LAN("06", "LA1", "la1.api.riotgames.com"),
    LAS("07", "LA2", "la2.api.riotgames.com"),
    NA("08", "NA1", "na1.api.riotgames.com"),
    OCE("09", "OC1", "oc1.api.riotgames.com"),
    TR("10", "TR1", "tr1.api.riotgames.com"),
    RU("11", "RU", "ru.api.riotgames.com"),
    PBE("12", "PBE1", "pbe1.api.riotgames.com");

    private String code;

    private String platform;

    private String host;

    ServiceProxyEnum(String code, String platform, String host) {
        this.code = code;
        this.platform = platform;
        this.host = host;
    }

    public String getHost() {
        return host;
    }

    public String getPlatform() {
        return platform;
    }

    @Override
    public String getCode() {
        return code;
    }
}
