package wsg.lol.pojo.enums.impl.others;

import wsg.lol.pojo.enums.intf.BaseEnum;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-01 16:49
 */
public enum RegionalProxyEnum implements BaseEnum<Integer> {
    Americas(1, "americas.api.riotgames.com"),
    Europe(2, "europe.api.riotgames.com"),
    Asia(3, "asia.api.riotgames.com");

    private int value;
    private String host;

    RegionalProxyEnum(int value, String host) {
        this.value = value;
        this.host = host;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public String getHost() {
        return host;
    }
}

