package wsg.lol.data.api;

import com.alibaba.fastjson.JSON;
import wsg.lol.common.base.IJSONTransfer;
import wsg.lol.common.base.QueryDto;
import wsg.lol.common.constants.DefaultConfig;
import wsg.lol.common.utils.BeanUtil;
import wsg.lol.common.utils.CodeUtil;
import wsg.lol.common.utils.HttpHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-02-27 16:33
 */
public class BaseApi {

    private static final String API_KEY = DefaultConfig.getApiKey();

    private static final String CURRENT_HOST = DefaultConfig.getRegion().getHost();

    private static final String GLOBAL_HOST = DefaultConfig.getGlobalProxy().getHost();

    private static String getJSONString(String host, String apiRef, Map<String, Object> pathParams, Map<String,
            Object> queryParams) {
        for (Map.Entry<String, Object> entry : pathParams.entrySet()) {
            apiRef = apiRef.replace("{" + entry.getKey() + "}", CodeUtil.encode(entry.getValue()));
        }
        String urlStr = HttpHelper.HTTPS + (host + apiRef + "?api_key=" + API_KEY
                + CodeUtil.encodeMap2UrlParams(queryParams)).replace("+", "%20");
        return HttpHelper.getJSONString(urlStr);
    }

    private static String getJSONString(String apiRef, Map<String, Object> pathParams,
                                        Map<String, Object> queryParams) {
        return getJSONString(CURRENT_HOST, apiRef, pathParams, queryParams);
    }

    static <T> T getCommonDataObject(String apiRef, Map<String, Object> pathParams, Class<T> clazz) {
        String jsonStr = getJSONString(GLOBAL_HOST, apiRef, pathParams, new HashMap<String, Object>());
        return JSON.parseObject(jsonStr, clazz);
    }

    static <Q extends QueryDto, T> T getDataObject(String apiRef, Map<String, Object> pathParams, Q queryDto,
                                                   Class<T> clazz) {
        String jsonStr = getJSONString(apiRef, pathParams, BeanUtil.getQueryParams(queryDto));
        return JSON.parseObject(jsonStr, clazz);
    }

    static <T> T getDataObject(String apiRef, Map<String, Object> pathParams, Class<T> clazz) {
        String jsonStr = getJSONString(apiRef, pathParams, new HashMap<String, Object>());
        return JSON.parseObject(jsonStr, clazz);
    }

    static <T> T getDataObject(String apiRef, Class<T> clazz) {
        return getDataObject(apiRef, new HashMap<String, Object>(), clazz);
    }

    static <T> List<T> getDataArray(String apiRef, Map<String, Object> pathParams, Class<T> clazz) {
        String jsonStr = getJSONString(apiRef, pathParams, new HashMap<String, Object>());
        return JSON.parseArray(jsonStr, clazz);
    }

    static <T> List<T> getDataArray(String apiRef, Class<T> clazz) {
        return getDataArray(apiRef, new HashMap<String, Object>(), clazz);
    }

    static <T extends IJSONTransfer> T getDataExtObject(String apiRef, Map<String, Object> pathParams, Class<T> clazz) {
        String jsonStr = getJSONString(apiRef, pathParams, new HashMap<String, Object>());
        T object = null;
        try {
            object = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        object.parseFromJSONObject(JSON.parseObject(jsonStr));
        return object;
    }

    static <T extends QueryDto, V extends QueryDto> String postJSONString(String apiRef, T queryDto,
                                                                          V postDto) {
        String urlStr = (GLOBAL_HOST + apiRef + "?api_key=" + API_KEY
                + (queryDto != null ? CodeUtil.encodeMap2UrlParams(BeanUtil.getQueryParams(queryDto)) : ""))
                .replace("+", "%20");
        return HttpHelper.postJSONString(urlStr, BeanUtil.getQueryParams(postDto));
    }

    static <T extends QueryDto> String postJSONString(String apiRef, T postDto) {
        return postJSONString(apiRef, null, postDto);
    }

    static String putJSONString(String apiRef, Map<String, Object> pathParams,
                                Map<String, Object> bodyParams) {
        for (Map.Entry<String, Object> entry : pathParams.entrySet()) {
            apiRef = apiRef.replace("{" + entry.getKey() + "}", CodeUtil.encode(entry.getValue()));
        }
        String urlStr = (GLOBAL_HOST + apiRef + "?api_key=" + API_KEY).replace("+", "%20");
        return HttpHelper.putJSONString(urlStr, bodyParams);
    }
}
