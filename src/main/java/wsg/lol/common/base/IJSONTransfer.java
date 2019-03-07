package wsg.lol.common.base;

import com.alibaba.fastjson.JSONObject;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-05 13:39
 */
public interface IJSONTransfer {
    void parseFromJSONObject(JSONObject jsonObject);
}
