package wsg.lol.common.enums.impl.others;

import wsg.lol.common.enums.intf.CodeEnum;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-01 16:49
 */
public enum RegionalProxyEnum implements CodeEnum {
    Americas("01", "americas.api.riotgames.com"),
    Europe("02", "europe.api.riotgames.com"),
    Asia("03", "asia.api.riotgames.com");

    private String code;
    private String host;

    RegionalProxyEnum(String code, String host) {
        this.code = code;
        this.host = host;
    }

    @Override
    public String getCode() {
        return code;
    }

    public String getHost() {
        return host;
    }
}

