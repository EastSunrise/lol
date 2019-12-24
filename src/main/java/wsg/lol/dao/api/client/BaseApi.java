package wsg.lol.dao.api.client;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wsg.lol.common.base.ApiHTTPException;
import wsg.lol.common.base.AppException;
import wsg.lol.common.base.QueryDto;
import wsg.lol.common.enums.system.RegionEnum;
import wsg.lol.common.enums.system.ResponseCodeEnum;
import wsg.lol.common.pojo.serialize.RecordExtraProcessor;
import wsg.lol.common.util.HttpHelper;
import wsg.lol.config.CustomParser;
import wsg.lol.config.DragonConfig;
import wsg.lol.config.RegionIdentifier;

import javax.validation.constraints.NotNull;
import javax.xml.ws.http.HTTPException;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
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

    private static final Logger logger = LoggerFactory.getLogger(BaseApi.class);

    private static final String HTTPS = "https://";

    private static final long RETRY_INTERVAL = 10000L;

    private static final int TIME_OUT = 30000;

    private static final CloseableHttpClient HTTP_CLIENT = HttpClients.createDefault();

    private static final RequestConfig REQUEST_CONFIG = RequestConfig.custom().setConnectTimeout(TIME_OUT).setSocketTimeout(TIME_OUT).build();

    private ApiClient apiClient;

    private DragonConfig dragonConfig;

    /**
     * Get single object.
     *
     * @param apiRef      the relative url of the api.
     * @param pathParams  params filled in the url
     * @param queryParams query params after the '?', joined with '&'.
     * @param clazz       the type of object parsed from the return.
     * @see <a href='{@link #doHttpGet}'></a>
     */
    protected <T extends Serializable> T getObject(String apiRef, Map<String, Object> pathParams, Map<String, Object> queryParams, Class<T> clazz) throws AppException, HTTPException {
        return CustomParser.parseObject(getJSONString(apiRef, pathParams, queryParams), clazz, new RecordExtraProcessor(BaseApi.class));
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
     * @see <a href='{@link #doHttpGet}'></a>
     */
    protected <T extends Serializable> List<T> getArray(String apiRef, Map<String, Object> pathParams, Map<String, Object> queryParams, Class<T> clazz) {
        return CustomParser.parseArray(getJSONString(apiRef, pathParams, queryParams), clazz);
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
        RegionEnum region = RegionIdentifier.getRegion();
        String urlStr = (region.getHost() + apiRef + urlParams).replace("+", "%20");

        // todo get from the api directly
        String filepath = StringUtils.joinWith(File.separator, dragonConfig.getDirectory(), "api", urlStr.replace("?", File.separator).replace("&", File.separator) + ".json");
        try {
            logger.info("Read from {}.", filepath);
            return FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.info("Can't read from {}.", filepath);
        }

        String text = doHttpGet(urlStr, region);
        try {
            logger.info("Write to {}.", filepath);
            FileUtils.writeStringToFile(new File(filepath), text, StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.error("Failed to write to {}.", filepath);
            e.printStackTrace();
        }
        return text;
    }

    /**
     * @throws ApiHTTPException BadRequest, NotFound, UnsupportedMediaType, Unauthorized.
     * @throws HTTPException    Other unknown response whose code isn't included in {@link ResponseCodeEnum}.
     */
    private String doHttpGet(@NotNull String urlStr, @NotNull RegionEnum region) throws ApiHTTPException, HTTPException {
        String format = HTTPS + urlStr + (urlStr.contains("?") ? "&" : "?") + "api_key=";
        int retryCount = 0;
        while (true) {
            CloseableHttpResponse response = null;
            try {
                urlStr = format + apiClient.getToken(region);
                HttpGet httpGet = setRequest(new HttpGet(urlStr));

                logger.info("Getting from " + urlStr);
                response = HTTP_CLIENT.execute(httpGet);

                int responseCode = response.getStatusLine().getStatusCode();
                if (ResponseCodeEnum.Success.getCode() == responseCode) {
                    HttpEntity entity = response.getEntity();
                    String text = EntityUtils.toString(entity);
                    EntityUtils.consume(entity);
                    return text;
                }

                String reason = response.getStatusLine().getReasonPhrase();
                if (ResponseCodeEnum.Forbidden.getCode() == responseCode) {
                    apiClient.occurForbidden(region);
                    continue;
                }
                if (ResponseCodeEnum.RateLimitExceeded.getCode() == responseCode) {
                    long retryAfter = Long.parseLong(response.getFirstHeader("Retry-After").getValue());
                    logger.warn("Rate limit exceeded. Wait for {} s.", retryAfter);
                    threadSleep(retryAfter * DateUtils.MILLIS_PER_SECOND);
                    continue;
                }
                if (ResponseCodeEnum.InternalServerError.getCode() == responseCode
                        || ResponseCodeEnum.ServiceUnavailable.getCode() == responseCode) {
                    logger.warn("{}. Retry the {} time.", reason, getOrdinalNumber(++retryCount));
                    threadSleep(RETRY_INTERVAL);
                    continue;
                }

                if (ResponseCodeEnum.BadRequest.getCode() == responseCode
                        || ResponseCodeEnum.NotFound.getCode() == responseCode
                        || ResponseCodeEnum.UnsupportedMediaType.getCode() == responseCode
                        || ResponseCodeEnum.Unauthorized.getCode() == responseCode) {
                    throw new ApiHTTPException(urlStr, responseCode);
                }
                logger.error("{}: {}.", reason, urlStr);
                throw new HTTPException(responseCode);
            } catch (IOException e) {
                logger.warn("{}. Retry the {} time.", e.getMessage(), getOrdinalNumber(++retryCount));
                threadSleep(RETRY_INTERVAL);
            } finally {
                if (response != null) {
                    try {
                        response.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private HttpGet setRequest(HttpGet httpGet) {
        httpGet.addHeader(HttpHeaders.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
        httpGet.addHeader(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate, br");
        httpGet.addHeader(HttpHeaders.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.9,zh-TW;q=0.8");
        httpGet.addHeader(HttpHeaders.CACHE_CONTROL, "max-age=0");
        httpGet.addHeader(HttpHeaders.CONNECTION, "keep-alive");
        httpGet.addHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.70 Safari/537.36");
        httpGet.setConfig(REQUEST_CONFIG);
        return httpGet;
    }

    private void threadSleep(long millis) {
        try {
            logger.info("Thread (" + Thread.currentThread().getName() + ") sleeps for " + millis + " ms.");
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String getOrdinalNumber(int num) {
        int i = num % 10;
        switch (i) {
            case 0:
                return "";
            case 1:
                return num + "st";
            case 2:
                return num + "nd";
            case 3:
                return num + "rd";
            default:
                return num + "th";
        }
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
