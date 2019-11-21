package wsg.lol.dao.api.client;

import com.alibaba.fastjson.JSON;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wsg.lol.common.base.AppException;
import wsg.lol.common.base.QueryDto;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.common.pojo.parser.RecordExtraProcessor;
import wsg.lol.common.util.HttpHelper;

import javax.xml.ws.http.HTTPException;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Base class for api.
 *
 * @author Kingen.
 */
@Component
public class BaseApi {

    private static final String HTTPS = "https://";

    private static final long TIME_OUT = DateUtils.MILLIS_PER_SECOND * 5;

    private static final Logger logger = LoggerFactory.getLogger(BaseApi.class);

    private ApiClient apiClient;

    /**
     * Get single object.
     *
     * @param apiRef     the relative url of the api.
     * @param pathParams params filled in the url
     * @param queryDto   query bean transferred to params appended at the end of the url.
     * @param clazz      the type of object parsed from the return.
     */
    protected <Q extends QueryDto, T extends Serializable> T getObject(String apiRef, Map<String, Object> pathParams, Q queryDto, Class<T> clazz) {
        String jsonStr = getJSONString(apiRef, pathParams, queryDto);
        return JSON.parseObject(jsonStr, clazz, new RecordExtraProcessor(BaseApi.class));
    }

    protected <T extends Serializable> T getObject(String apiRef, Map<String, Object> pathParams, Class<T> clazz) {
        return getObject(apiRef, pathParams, null, clazz);
    }

    protected <T extends Serializable> T getObject(String apiRef, Class<T> clazz) {
        return getObject(apiRef, null, clazz);
    }

    /**
     * Get multiple objects.
     *
     * @param apiRef     the relative url of the api.
     * @param pathParams params filled in the url
     * @param queryDto   query bean transferred to params appended at the end of the url.
     * @param clazz      the type of the single object parsed from the return.
     */
    protected <Q extends QueryDto, T extends Serializable> List<T> getArray(String apiRef, Map<String, Object> pathParams, Q queryDto, Class<T> clazz) {
        String jsonStr = getJSONString(apiRef, pathParams, queryDto);
        return JSON.parseArray(jsonStr, clazz);
    }

    protected <T extends Serializable> List<T> getArray(String apiRef, Map<String, Object> pathParams, Class<T> clazz) {
        return getArray(apiRef, pathParams, null, clazz);
    }

    protected <T extends Serializable> List<T> getArray(String apiRef, Class<T> clazz) {
        return getArray(apiRef, null, clazz);
    }

    private String doHttpGet(String urlStr) {
        logger.info("Getting from " + urlStr);
        while (true) {
            try {
                URL url = new URL(urlStr);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout((int) TIME_OUT);
                urlConnection.setReadTimeout((int) TIME_OUT);
                urlConnection.setRequestProperty("X-Riot-Token", apiClient.getToken());
                urlConnection.setRequestProperty("Origin", "https://developer.riotgames.com");
                urlConnection.setRequestProperty("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
                urlConnection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,zh-TW;q=0.8");
                urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36");

                int responseCode = urlConnection.getResponseCode();
                if (ResponseCodeEnum.Success.getCode() == responseCode) {
                    StringBuilder builder = new StringBuilder();
                    BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        builder.append(inputLine);
                    }
                    in.close();
                    return builder.toString();
                }

                logger.info(urlConnection.getResponseMessage());
                if (ResponseCodeEnum.RateLimitExceeded.getCode() == responseCode) {
                    long retryAfter = Long.parseLong(urlConnection.getHeaderField("Retry-After")) * DateUtils.MILLIS_PER_SECOND;
                    threadSleep(retryAfter);
                    continue;
                }
                if (ResponseCodeEnum.Unauthorized.getCode() == responseCode) {
                    apiClient.regenerateToken();
                    continue;
                }
                throw new HTTPException(responseCode);
            } catch (IOException e) {
                e.printStackTrace();
                threadSleep(TIME_OUT);
            }
        }
    }

    private <Q> String getJSONString(String apiRef, Map<String, Object> pathParams, Q query) {
        Map<String, Object> queryParams = new HashMap<>();
        try {
            BeanUtils.populate(query, queryParams);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new AppException(ErrorCodeConst.SYSTEM_ERROR, e);
        }
        if (MapUtils.isNotEmpty(pathParams)) {
            for (Map.Entry<String, Object> entry : pathParams.entrySet()) {
                apiRef = apiRef.replace("{" + entry.getKey() + "}", HttpHelper.encode(entry.getValue()));
            }
        }

        String urlStr = HTTPS + (apiClient.getRegion().getHost() + apiRef + "?" + HttpHelper.encodeMap2UrlParams(queryParams)).replace("+", "%20");

        // TODO: (Kingen, 2019/11/21) to delete
        String filename = "api/" + urlStr.substring(HTTPS.length()).replace("?", "#") + ".json";
        try {
            logger.info("Read from {}.", filename);
            return FileUtils.readFileToString(new File(filename), StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.info("Can't read from {}.", filename);
        }

        String jsonStr = doHttpGet(urlStr);
        try {
            logger.info("Write to {}.", filename);
            FileUtils.writeStringToFile(new File(filename), jsonStr, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    private void threadSleep(long millis) {
        try {
            logger.info("Thread (" + Thread.currentThread().getName() + ") sleeps for " + millis + " ms.");
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }
}
