package wsg.lol.common.base;

import wsg.lol.common.util.EnumUtils;
import wsg.lol.dao.api.client.ResponseCodeEnum;

import javax.xml.ws.http.HTTPException;

/**
 * Exception for failed api response.
 *
 * @author Kingen
 */
public class ApiHTTPException extends HTTPException {

    private String url;

    private ResponseCodeEnum responseCode;

    public ApiHTTPException(String url, int statusCode) {
        super(statusCode);
        this.url = url;
        this.responseCode = EnumUtils.parseFromObject(statusCode, ResponseCodeEnum.class);
    }

    public ApiHTTPException(String url, ResponseCodeEnum responseCode) {
        super(responseCode.getCode());
        this.url = url;
        this.responseCode = responseCode;
    }

    public ResponseCodeEnum getResponseCode() {
        return responseCode;
    }

    public String getUrl() {
        return url;
    }
}
