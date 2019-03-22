package wsg.lol.pojo.exception;

import javax.xml.ws.http.HTTPException;
import java.util.List;
import java.util.Map;

/**
 * wsg
 *
 * @author wangsigen
 */
public class MyHTTPException extends HTTPException {

    private Map<String, List<String>> headerFields;

    /**
     * Constructor for the HTTPException
     *
     * @param statusCode
     *         <code>int</code> for the HTTP status code
     **/
    public MyHTTPException(int statusCode, Map<String, List<String>> headerFields) {
        super(statusCode);
        this.headerFields = headerFields;
    }

    public Map<String, List<String>> getHeaderFields() {
        return headerFields;
    }
}
