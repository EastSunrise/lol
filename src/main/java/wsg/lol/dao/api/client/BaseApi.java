package wsg.lol.dao.api.client;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wsg.lol.common.base.AppException;
import wsg.lol.common.base.QueryDto;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.common.util.EnumUtils;
import wsg.lol.common.util.HttpHelper;
import wsg.lol.dao.GlobalConfig;
import wsg.lol.dao.common.serialize.RecordExtraProcessor;
import wsg.lol.dao.dragon.config.DragonConfig;

import javax.xml.ws.http.HTTPException;
import java.io.*;
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

    private static final long RETRY_INTERVAL = 10000L;

    private static final Logger logger = LoggerFactory.getLogger(BaseApi.class);

    private ApiClient apiClient;

    private DragonConfig dragonConfig;

    private GlobalConfig globalConfig;

    /**
     * Get single object.
     *
     * @param apiRef      the relative url of the api.
     * @param pathParams  params filled in the url
     * @param queryParams query params after the '?', joined with '&'.
     * @param clazz       the type of object parsed from the return.
     * @throws AppException  if occurs {@link IOException}
     * @throws HTTPException with {@link HTTPException#getStatusCode()}
     */
    protected <T extends Serializable> T getObject(String apiRef, Map<String, Object> pathParams, Map<String, Object> queryParams, Class<T> clazz) throws AppException, HTTPException {
        String jsonStr = getJSONString(apiRef, pathParams, queryParams);
        return JSON.parseObject(jsonStr, clazz, new RecordExtraProcessor(BaseApi.class));
    }

    protected <Q extends QueryDto, T extends Serializable> T getObject(String apiRef, Map<String, Object> pathParams, Q queryDto, Class<T> clazz) {
        return getObject(apiRef, pathParams, queryDto.toMap(), clazz);
    }

    protected <T extends Serializable> T getObject(String apiRef, Map<String, Object> pathParams, Class<T> clazz) {
        return getObject(apiRef, pathParams, new HashMap<>(), clazz);
    }

    protected <T extends Serializable> T getObject(String apiRef, Class<T> clazz) {
        return getObject(apiRef, null, clazz);
    }

    /**
     * Get multiple objects.
     *
     * @param apiRef      the relative url of the api.
     * @param pathParams  params filled in the url
     * @param queryParams query params after the '?', joined with '&'.
     * @param clazz       the type of the single object parsed from the return.
     */
    protected <T extends Serializable> List<T> getArray(String apiRef, Map<String, Object> pathParams, Map<String, Object> queryParams, Class<T> clazz) {
        String jsonStr = getJSONString(apiRef, pathParams, queryParams);
        return JSON.parseArray(jsonStr, clazz);
    }

    protected <T extends Serializable> List<T> getArray(String apiRef, Map<String, Object> pathParams, Class<T> clazz) {
        return getArray(apiRef, pathParams, new HashMap<>(), clazz);
    }

    private String getJSONString(String apiRef, Map<String, Object> pathParams, Map<String, Object> queryParams) throws HTTPException, AppException {
        if (MapUtils.isNotEmpty(pathParams)) {
            for (Map.Entry<String, Object> entry : pathParams.entrySet()) {
                apiRef = apiRef.replace("{" + entry.getKey() + "}", HttpHelper.encode(entry.getValue()));
            }
        }

        String urlParams = HttpHelper.encodeMap2UrlParams(queryParams);
        if (StringUtils.isNotEmpty(urlParams)) {
            urlParams = "?" + urlParams;
        }
        String urlStr = (globalConfig.getRegion().getHost() + apiRef + urlParams).replace("+", "%20");

        // todo get from the api directly
        String filepath = StringUtils.joinWith(File.separator, dragonConfig.getCdnDir(), "api", urlStr.replace("?", File.separator).replace("&", File.separator) + ".json");
        try {
            logger.info("Read from {}.", filepath);
            return FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.info("Can't read from {}.", filepath);
        }

        String jsonStr = doHttpGet(HTTPS + urlStr);
        try {
            logger.info("Write to {}.", filepath);
            FileUtils.writeStringToFile(new File(filepath), jsonStr, StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.error("Failed to write to {}.", filepath);
            e.printStackTrace();
        }
        return jsonStr;
    }

    private String doHttpGet(String urlStr) throws AppException, HTTPException {
        logger.info("Getting from " + urlStr);
        int retryCount = 0;
        while (true) {
            try {
                URL url = new URL(urlStr);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(apiClient.getTimeout());
                urlConnection.setReadTimeout(apiClient.getTimeout());
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

                if (ResponseCodeEnum.BadRequest.getCode() == responseCode
                        || ResponseCodeEnum.Forbidden.getCode() == responseCode
                        || ResponseCodeEnum.NotFound.getCode() == responseCode
                        || ResponseCodeEnum.UnsupportedMediaType.getCode() == responseCode
                        || ResponseCodeEnum.Unauthorized.getCode() == responseCode) {
                    ResponseCodeEnum responseCodeEnum = EnumUtils.parseFromObject(responseCode, ResponseCodeEnum.class);
                    logger.info("{}: {}.", responseCodeEnum.getMessage(), urlStr);
                    throw new HTTPException(responseCode);
                }
                if (ResponseCodeEnum.RateLimitExceeded.getCode() == responseCode) {
                    long retryAfter = Long.parseLong(urlConnection.getHeaderField("Retry-After"));
                    logger.error("Rate limit exceeded. Wait for {} s.", retryAfter);
                    threadSleep(retryAfter * DateUtils.MILLIS_PER_SECOND);
                    continue;
                }
                if (ResponseCodeEnum.InternalServerError.getCode() == responseCode
                        || ResponseCodeEnum.ServiceUnavailable.getCode() == responseCode) {
                    ResponseCodeEnum responseCodeEnum = EnumUtils.parseFromObject(responseCode, ResponseCodeEnum.class);
                    logger.error(responseCodeEnum.getMessage());
                    threadSleep(RETRY_INTERVAL);
                    continue;
                }
                logger.error(urlConnection.getResponseMessage());
                throw new HTTPException(responseCode);
            } catch (IOException e) {
                logger.error(e.getMessage());
                retryCount++;
                if (retryCount > 1) {
                    throw new AppException(ErrorCodeConst.SYSTEM_ERROR, e);
                }
                threadSleep(RETRY_INTERVAL);
            }
        }
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
    public void setGlobalConfig(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
    }

    @Autowired
    public void setDragonConfig(DragonConfig dragonConfig) {
        this.dragonConfig = dragonConfig;
    }

    @Autowired
    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }
}
