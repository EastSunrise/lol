package wsg.lol.dao.api.client;

import wsg.lol.common.pojo.serialize.IntSerializable;

/**
 * Table of response codes of the api.
 *
 * @author Kingen
 * @see <a href="https://developer.riotgames.com/docs/portal#web-apis_response-codes">Response Codes</a>
 */
public enum ResponseCodeEnum implements IntSerializable {
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

    @Override
    public int serializeInt() {
        return code;
    }
}
