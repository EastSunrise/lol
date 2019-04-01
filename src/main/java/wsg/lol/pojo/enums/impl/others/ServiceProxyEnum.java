package wsg.lol.pojo.enums.impl.others;

import wsg.lol.pojo.base.BaseEnum;

/**
 * @author King
 */
public enum ServiceProxyEnum implements BaseEnum<Integer> {
    BR(1, "BR1", "br1.api.riotgames.com"),
    EUNE(2, "EUN1", "eun1.api.riotgames.com"),
    EUW(3, "EUW1", "euw1.api.riotgames.com"),
    JP(4, "JP1", "jp1.api.riotgames.com"),
    KR(5, "KR", "kr.api.riotgames.com"),
    LAN(6, "LA1", "la1.api.riotgames.com"),
    LAS(7, "LA2", "la2.api.riotgames.com"),
    NA(8, "NA1", "na1.api.riotgames.com"),
    OCE(9, "OC1", "oc1.api.riotgames.com"),
    TR(10, "TR1", "tr1.api.riotgames.com"),
    RU(11, "RU", "ru.api.riotgames.com"),
    PBE(12, "PBE1", "pbe1.api.riotgames.com");

    private int value;

    private String platform;

    private String host;

    ServiceProxyEnum(int value, String platform, String host) {
        this.value = value;
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
    public Integer getValue() {
        return value;
    }
}
