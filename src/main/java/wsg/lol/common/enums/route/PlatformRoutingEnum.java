package wsg.lol.common.enums.route;

/**
 * Enums for routes of platforms.
 *
 * @author Kingen
 * @see <a href="https://developer.riotgames.com/docs/lol#_routing-values">Routing Values</a>
 */
public enum PlatformRoutingEnum {
    LOL("api.riotgames.com"),
    BR("br1.api.riotgames.com"),
    EUN("eun1.api.riotgames.com"),
    EUW("euw1.api.riotgames.com"),
    JP("jp1.api.riotgames.com"),
    KR("kr.api.riotgames.com"),
    LA1("la1.api.riotgames.com"),
    LA2("la2.api.riotgames.com"),
    NA("na1.api.riotgames.com"),
    OC("oc1.api.riotgames.com"),
    TR("tr1.api.riotgames.com"),
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
