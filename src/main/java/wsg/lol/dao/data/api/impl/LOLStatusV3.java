package wsg.lol.dao.data.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wsg.lol.dao.data.api.base.intf.BaseApi;
import wsg.lol.pojo.annotation.AccessApi;
import wsg.lol.pojo.dto.api.share.ShardStatus;

/**
 * @author King
 */
@Component
public class LOLStatusV3 {

    private BaseApi baseApi;

    /**
     * wsg 未存DB
     * Get League of Legends status for the given shard.
     * <p>
     * Requests to this API are not counted against the application Rate Limits.
     */
    @AccessApi
    public ShardStatus getSharedData() {
        return baseApi.getObject("/lol/status/v3/shard-data", ShardStatus.class);
    }

    @Autowired
    public void setBaseApi(BaseApi baseApi) {
        this.baseApi = baseApi;
    }
}
