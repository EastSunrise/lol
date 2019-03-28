package wsg.lol.pojo.base;

import com.alibaba.fastjson.JSONObject;

/**
 * wsg
 *
 * @author wangsigen
 */
public interface IJSONTransfer {
    void parseFromJSONObject(JSONObject jsonObject);
}
