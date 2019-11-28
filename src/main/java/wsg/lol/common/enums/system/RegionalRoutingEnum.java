package wsg.lol.common.enums.system;

/**
 * Enums for routes of regions.
 *
 * @author Kingen
 * @see <a href="https://developer.riotgames.com/docs/lol#_routing-values">Routing Values</a>
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
