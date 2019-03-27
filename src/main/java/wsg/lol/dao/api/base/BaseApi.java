package wsg.lol.dao.api.base;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wsg.lol.common.utils.BeanUtil;
import wsg.lol.common.utils.CodeUtil;
import wsg.lol.common.utils.DateUtil;
import wsg.lol.common.utils.LogUtil;
import wsg.lol.dao.config.ApiConfig;
import wsg.lol.pojo.base.IJSONTransfer;
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

    private ApiConfig apiConfig;

    // get valid api key
    // limit the rate of querying.
    // exclusive access
    private synchronized Map<String, String> getRequestHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Origin", "https://developer.riotgames.com");
        headers.put("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
        while (!apiConfig.hasValidKey()) {
            LogUtil.info("There isn't valid key.");
            DateUtil.threadSleep(DateUtil.ONE_SECOND);
        }
        headers.put("X-Riot-Token", apiConfig.getApiKey());
        headers.put("Accept-Language", "zh-CN,zh;q=0.9,zh-TW;q=0.8");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) " +
                "Chrome/72.0.3626.121 Safari/537.36");
        return headers;
    }

    private String getJSONString(String apiRef, Map<String, Object> pathParams,
                                 Map<String, Object> queryParams) {
        return getJSONString(apiConfig.getRegion().getHost(), apiRef, pathParams, queryParams);
    }

    private String doHttpGet(String urlStr, Map<String, String> requestHeaders) {
        LogUtil.info("Getting from " + urlStr);
        while (true) {
            try {
                URL url = new URL(urlStr);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(DateUtil.ONE_SECOND * 5);
                urlConnection.setReadTimeout(DateUtil.ONE_SECOND * 5);
                for (Map.Entry<String, String> entry : requestHeaders.entrySet()) {
                    urlConnection.setRequestProperty(entry.getKey(), entry.getValue());
                }

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
                } else if (429 == responseCode) {
                    LogUtil.info(urlConnection.getResponseMessage());
                    DateUtil.threadSleep(Integer.valueOf(urlConnection.getHeaderField("Retry-After")) * DateUtil.ONE_SECOND);
                } else if (500 == responseCode || 503 == responseCode) {
                    LogUtil.info(urlConnection.getResponseMessage());
                    DateUtil.threadSleep(DateUtil.ONE_MINUTE);
                } else {
                    LogUtil.info(urlConnection.getResponseMessage());
                    throw new HTTPException(responseCode);
                }
            } catch (IOException e) {
                e.printStackTrace();
                DateUtil.threadSleep(DateUtil.ONE_SECOND * 5);
            }
        }
    }

    protected <Q extends QueryDto, T> T getDataObject(String apiRef, Map<String, Object> pathParams, Q queryDto,
                                                      Class<T> clazz) {
        String jsonStr = getJSONString(apiRef, pathParams, BeanUtil.getQueryParams(queryDto));
        return JSON.parseObject(jsonStr, clazz);
    }

    protected <T> T getDataObject(String apiRef, Map<String, Object> pathParams, Class<T> clazz) {
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
        return doHttpGet(urlStr, getRequestHeaders());
    }

    protected <T> T getDataObject(String apiRef, Class<T> clazz) {
        return getDataObject(apiRef, new HashMap<>(), clazz);
    }

    protected <T> List<T> getDataArray(String apiRef, Class<T> clazz) {
        return getDataArray(apiRef, new HashMap<>(), clazz);
    }

    protected <T> List<T> getDataArray(String apiRef, Map<String, Object> pathParams, Class<T> clazz) {
        String jsonStr = getJSONString(apiRef, pathParams, new HashMap<>());
        return JSON.parseArray(jsonStr, clazz);
    }

    protected <T extends IJSONTransfer> T getDataExtObject(String apiRef, Map<String, Object> pathParams,
                                                           Class<T> clazz) {
        String jsonStr = getJSONString(apiRef, pathParams, new HashMap<>());
        T object = null;
        try {
            object = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        if (object != null) {
            object.parseFromJSONObject(JSON.parseObject(jsonStr));
        }
        return object;
    }

    @Autowired
    public void setApiConfig(ApiConfig apiConfig) {
        this.apiConfig = apiConfig;
    }
}
