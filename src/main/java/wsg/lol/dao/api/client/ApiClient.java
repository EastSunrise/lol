package wsg.lol.dao.api.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import wsg.lol.common.enums.route.PlatformRoutingEnum;

import java.util.Date;

/**
 * 模拟用户客户端
 *
 * @author EastSunrise
 * @date 2019/11/6
 * @since 1.0
 */
@Component
public class ApiClient {

    /**
     * 用户名
     */
    @Value("${api.username}")
    private String username;

    /**
     * 密码
     */
    @Value("${api.password}")
    private String password;

    /**
     * 地区
     */
    @Value("${api.region}")
    private PlatformRoutingEnum region;

    /**
     * api key
     */
    private String token;

    /**
     * 失效时间
     */
    private Date expiresTime;

    /**
     * 获取当前用户token
     * 如果失效，进行重置
     */
    public String getToken() {
        if (!hasValidToken()) {
            regenerateToken();
        }
        return this.token;
    }

    public boolean hasValidToken() {
        return !StringUtils.isEmpty(this.token) && this.expiresTime.compareTo(new Date()) > 0;
    }

    public void regenerateToken() {
        // TODO: (wangsigen, 2019/11/6) regenerate token
        this.token = "";
        this.expiresTime = new Date();
    }

    public PlatformRoutingEnum getRegion() {
        return region;
    }
}
