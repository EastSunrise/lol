package wsg.lol.dao.api.client;

/**
 * api请求响应码枚举
 *
 * @author EastSunrise
 * @date 2019/11/6
 * @see <a href="https://developer.riotgames.com/docs/portal#web-apis_response-codes">Response Codes</a>
 * @since 1.0
 */
public enum ResponseCodeEnum {
    Success(200, "Success"),
    BadRequest(400, "Bad Request"),
    Unauthorized(401, "Unauthorized"),
    Forbidden(403, "Forbidden"),
    NotFound(404, "Not Found"),
    UnsupportedMediaType(415, "Unsupported Media Type"),
    RateLimitExceeded(429, "Rate Limit Exceeded"),
    InternalServerError(500, "Internal Server Error"),
    ServiceUnavailable(503, "Service Unavailable"),
    ;

    private int code;
    private String message;

    ResponseCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
