package wsg.lol.common.enums.system;

import wsg.lol.dao.common.serialize.EqualsToSerializable;
import wsg.lol.dao.common.serialize.JSONSerializable;

/**
 * Enums for routes of platforms.
 *
 * @author Kingen
 * @see <a href="https://developer.riotgames.com/docs/lol#_routing-values">Routing Values</a>
 */
public enum PlatformRoutingEnum implements JSONSerializable<String>, EqualsToSerializable<String> {
    LOL("api.riotgames.com", "LOL"),
    BR("br1.api.riotgames.com", "BR1", "巴西"),
    EUNE("eun1.api.riotgames.com", "EUN1", "北欧及东欧"),
    EUW("euw1.api.riotgames.com", "EUW1", "西欧"),
    JP("jp1.api.riotgames.com", "JP1", "日本"),
    KR("kr.api.riotgames.com", "KR", "韩国"),
    LAN("la1.api.riotgames.com", "LA1", "北拉丁美洲"),
    LAS("la2.api.riotgames.com", "LA2", "南拉丁美洲"),
    NA("na1.api.riotgames.com", "NA1", "北美"),
    OCE("oc1.api.riotgames.com", "OC1", "大洋洲"),
    RU("ru.api.riotgames.com", "RU", "俄罗斯"),
    TR("tr1.api.riotgames.com", "TR1", "土耳其"),
    NULL("", ""),
    ;

    private String host;

    private String id;

    private String title;

    PlatformRoutingEnum(String host, String id) {
        this.host = host;
        this.id = id;
    }

    PlatformRoutingEnum(String host, String id, String title) {
        this.host = host;
        this.id = id;
        this.title = title;
    }

    public String getHost() {
        return host;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String serialize() {
        return getId();
    }

    @Override
    public boolean equalsToObject(String string) {
        return this.getId().equalsIgnoreCase(string) || name().equalsIgnoreCase(string);
    }
}
