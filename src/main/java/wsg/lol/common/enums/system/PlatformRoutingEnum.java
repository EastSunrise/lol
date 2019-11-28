package wsg.lol.common.enums.system;

import wsg.lol.common.pojo.serialize.StringSerializable;

/**
 * Enums for routes of platforms.
 *
 * @author Kingen
 * @see <a href="https://developer.riotgames.com/docs/lol#_routing-values">Routing Values</a>
 */
public enum PlatformRoutingEnum implements StringSerializable {
    LOL("api.riotgames.com"),
    BR("br1.api.riotgames.com", "BR1"),
    EUN("eun1.api.riotgames.com", "EUN1"),
    EUW("euw1.api.riotgames.com", "EUW1"),
    JP("jp1.api.riotgames.com", "JP1"),
    KR("kr.api.riotgames.com", "KR"),
    LA1("la1.api.riotgames.com", "LA1"),
    LA2("la2.api.riotgames.com", "LA2"),
    NA("na1.api.riotgames.com", "NA1"),
    OC("oc1.api.riotgames.com", "OC1"),
    TR("tr1.api.riotgames.com", "TR1"),
    RU("ru.api.riotgames.com", "RU"),
    ;

    private String host;

    private String id;

    PlatformRoutingEnum(String host, String id) {
        this.host = host;
        this.id = id;
    }

    PlatformRoutingEnum(String host) {
        this.host = host;
    }

    public String getHost() {
        return host;
    }

    public String getId() {
        return id;
    }

    @Override
    public String serialize() {
        return getId();
    }
}
