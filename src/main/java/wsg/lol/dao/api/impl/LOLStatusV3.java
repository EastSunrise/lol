package wsg.lol.dao.api.impl;

import org.springframework.stereotype.Component;
import wsg.lol.common.annotation.AccessApi;
import wsg.lol.dao.api.base.BaseApi;
import wsg.lol.pojo.dto.api.share.ShardStatus;

/**
 * @author King
 */
@Component
public class LOLStatusV3 extends BaseApi {

    /**
     * wsg 未存DB
     * Get League of Legends status for the given shard.
     * <p>
     * Requests to this API are not counted against the application Rate Limits.
     */
    @AccessApi
    public ShardStatus getSharedData() {
        return getObject("/lol/status/v3/shard-data", ShardStatus.class);
    }
}
