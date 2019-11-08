package wsg.lol.common.enums.route;

/**
 * 平台地址枚举
 *
 * @author EastSunrise
 * @date 2019/11/7
 * @see <a href="https://developer.riotgames.com/docs/lol#_routing-values">Routing Values</a>
 * @since 1.0
 */
public enum PlatformRoutingEnum {
    BR1("br1.api.riotgames.com"),
    EUN1("eun1.api.riotgames.com"),
    EUW1("euw1.api.riotgames.com"),
    JP1("jp1.api.riotgames.com"),
    KR("kr.api.riotgames.com"),
    LA1("la1.api.riotgames.com"),
    LA2("la2.api.riotgames.com"),
    NA1("na1.api.riotgames.com"),
    OC1("oc1.api.riotgames.com"),
    TR1("tr1.api.riotgames.com"),
    RU("ru.api.riotgames.com"),
    ;

    private String host;

    PlatformRoutingEnum(String host) {
        this.host = host;
    }

    public String getHost() {
        return host;
    }
}
