package wsg.lol.common.util;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * // TODO: (Kingen, 2019/11/18)
 *
 * @author EastSunrise
 */
public class HttpHelper {

    private static final Logger logger = LoggerFactory.getLogger(HttpHelper.class);

    /**
     * 从指定的URL获取json字符串
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

    public static void downloadData(String urlStr, String savePath) {
        logger.info("Downloading from: " + urlStr + "  to: " + savePath);
        File saveFile = new File(savePath);
        File dir = saveFile.getParentFile();
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                try {
                    throw new Exception("创建目录失败：" + dir.getPath());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        URL url;
        try {
            url = new URL(urlStr);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;
            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());

            dataInputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
