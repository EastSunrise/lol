package wsg.lol.common.util;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Util for http connection.
 *
 * @author Kingen
 */
public class HttpHelper {

    private static final Logger logger = LoggerFactory.getLogger(HttpHelper.class);

    /**
     * Get data from the url.
     */
    public static String getString(String urlStr) {
        StringBuilder builder = new StringBuilder();
        try {
            URL url = new URL(urlStr);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            try {
                while ((inputLine = reader.readLine()) != null) {
                    builder.append(inputLine);
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    /**
     * encode str by UTF-8
     */
    public static String encode(Object object) {
        if (object == null) {
            return "";
        }
        try {
            return URLEncoder.encode(object.toString(), StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            return "";
        }
    }

    /**
     * parse url parameters from map
     */
    public static String encodeMap2UrlParams(Map<String, Object> params) {
        if (MapUtils.isEmpty(params)) {
            return "";
        }
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            Object value = entry.getValue();
            String key = entry.getKey();
            if (value != null) {
                if (value instanceof Collection) {
                    for (Object object : (Collection) value) {
                        if (object != null) {
                            list.add(key + "=" + encode(object));
                        }
                    }
                } else {
                    list.add(key + "=" + encode(value));
                }
            }
        }
        return StringUtils.join(list, "&");
    }
}
