package wsg.lol.common.enums.route;

/**
 * 地区地址枚举
 *
 * @author EastSunrise
 * @date 2019/11/7
 * @see <a href="https://developer.riotgames.com/docs/lol#_routing-values">Routing Values</a>
 * @since 1.0
 */
public enum RegionalRoutingEnum {
    AMERICAS("americas.api.riotgames.com"),
    ASIA("asia.api.riotgames.com"),
    EUROPE("europe.api.riotgames.com"),
    ;


    private String host;

    RegionalRoutingEnum(String host) {
        this.host = host;
    }

    public String getHost() {
        return host;
    }
}
