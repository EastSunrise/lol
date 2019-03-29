package wsg.lol.dao.api.base;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wsg.lol.common.utils.BeanUtil;
import wsg.lol.common.utils.CodeUtil;
import wsg.lol.common.utils.DateUtil;
import wsg.lol.dao.config.ApiConfig;
import wsg.lol.pojo.base.QueryDto;

import javax.xml.ws.http.HTTPException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * wsg
 *
 * @author wangsigen
 */
@Component
public class BaseApi {

    private static final String HTTPS = "https://";

    private static Logger logger = LoggerFactory.getLogger(BaseApi.class);

    private ApiConfig apiConfig;

    private static void threadSleep(long millis) {
        try {
            logger.info("Thread (" + Thread.currentThread().getName() + ") sleeps for " + millis / 1000 + "s");
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String getJSONString(String apiRef, Map<String, Object> pathParams,
                                 Map<String, Object> queryParams) {
        return getJSONString(apiConfig.getRegion().getHost(), apiRef, pathParams, queryParams);
    }

    private synchronized String doHttpGet(String urlStr) {
        logger.info("Getting from " + urlStr);
        while (true) {
            try {
                URL url = new URL(urlStr);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(DateUtil.ONE_SECOND * 5);
                urlConnection.setReadTimeout(DateUtil.ONE_SECOND * 5);
                Map<String, String> headers = new HashMap<>();
                while (!apiConfig.hasValidKey()) {
                    threadSleep(DateUtil.ONE_HOUR);
                }
                urlConnection.setRequestProperty("X-Riot-Token", apiConfig.getApiKey());
                urlConnection.setRequestProperty("Origin", "https://developer.riotgames.com");
                urlConnection.setRequestProperty("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
                urlConnection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,zh-TW;q=0.8");
                urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) " +
                        "Chrome/72.0.3626.121 Safari/537.36");

                int responseCode = urlConnection.getResponseCode();
                if (responseCode == 200) {
                    StringBuilder builder = new StringBuilder();
                    BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        builder.append(inputLine);
                    }
                    in.close();
                    return builder.toString();
                } else {
                    logger.info(urlConnection.getResponseMessage());
                    if (429 == responseCode) {
                        threadSleep(Integer.valueOf(urlConnection.getHeaderField("Retry-After")) * DateUtil.ONE_SECOND);
                    } else if (500 == responseCode || 503 == responseCode) {
                        threadSleep(DateUtil.ONE_MINUTE);
                    } else {
                        throw new HTTPException(responseCode);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                threadSleep(DateUtil.ONE_SECOND * 5);
            }
        }
    }

    protected <Q extends QueryDto, T> T getObject(String apiRef, Map<String, Object> pathParams, Q queryDto,
                                                  Class<T> clazz) {
        String jsonStr = getJSONString(apiRef, pathParams, BeanUtil.getQueryParams(queryDto));
        return JSON.parseObject(jsonStr, clazz);
    }

    protected <T> T getObject(String apiRef, Map<String, Object> pathParams, Class<T> clazz) {
        String jsonStr = getJSONString(apiRef, pathParams, new HashMap<>());
        return JSON.parseObject(jsonStr, clazz);
    }

    private String getJSONString(String host, String apiRef, Map<String, Object> pathParams, Map<String,
            Object> queryParams) {
        for (Map.Entry<String, Object> entry : pathParams.entrySet()) {
            apiRef = apiRef.replace("{" + entry.getKey() + "}", CodeUtil.encode(entry.getValue()));
        }
        String urlStr = HTTPS + (host + apiRef + "?" + CodeUtil.encodeMap2UrlParams(queryParams)).replace(
                "+", "%20");
        return doHttpGet(urlStr);
    }

    protected <T> T getObject(String apiRef, Class<T> clazz) {
        return getObject(apiRef, new HashMap<>(), clazz);
    }

    protected <T> List<T> getArray(String apiRef, Class<T> clazz) {
        return getArray(apiRef, new HashMap<>(), clazz);
    }

    protected <T> List<T> getArray(String apiRef, Map<String, Object> pathParams, Class<T> clazz) {
        String jsonStr = getJSONString(apiRef, pathParams, new HashMap<>());
        return JSON.parseArray(jsonStr, clazz);
    }


    @Autowired
    public void setApiConfig(ApiConfig apiConfig) {
        this.apiConfig = apiConfig;
    }
}
