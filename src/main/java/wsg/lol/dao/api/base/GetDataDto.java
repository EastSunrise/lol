package wsg.lol.dao.api.base;

import java.util.Date;
import java.util.Map;

/**
 * wsg
 *
 * @author wangsigen
 */
public class GetDataDto {

    private String apiKey;
    private Date expiredTime;

    private String host;
    private String apiRef;
    private Map<String, Object> pathParams;
    private Map<String, Object> queryParams;

    public GetDataDto(String host, String apiRef, Map<String, Object> pathParams, Map<String, Object> queryParams) {
        this.host = host;
        this.apiRef = apiRef;
        this.pathParams = pathParams;
        this.queryParams = queryParams;
    }

    public String getApiKey() {
        return apiKey;
    }

    public Date getExpiredTime() {
        return expiredTime;
    }

    public String getHost() {
        return host;
    }

    public String getApiRef() {
        return apiRef;
    }

    public Map<String, Object> getPathParams() {
        return pathParams;
    }

    public Map<String, Object> getQueryParams() {
        return queryParams;
    }
}
