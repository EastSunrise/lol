package wsg.lol.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Map;

/**
 * @author King
 * @date 2019/2/11
 */
public class CodeUtil {

    public static String encode(Object object) {
        try {
            return URLEncoder.encode(object.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "Issue while encoding" + e.getMessage();
        }
    }

    public static String encodeMap2UrlParams(Map<String, Object> params) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            Object value = entry.getValue();
            String key = entry.getKey();
            if (value instanceof Collection) {
                for (Object object : (Collection) value) {
                    builder.append("&").append(key).append("=").append(CodeUtil.encode(object));
                }
            } else {
                builder.append("&").append(key).append("=").append(CodeUtil.encode(value));
            }
        }
        return builder.toString();
    }
}
