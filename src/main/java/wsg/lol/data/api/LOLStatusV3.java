package wsg.lol.data.api;

import wsg.lol.dto.api.others.ShardStatus;

/**
 * @author King
 * @date 2019/2/11
 */
public class LOLStatusV3 extends BaseApi {

    /**
     * wsg 未存DB
     * Get League of Legends status for the given shard.
     * <p>
     * Requests to this API are not counted against the application Rate Limits.
     */
    public static ShardStatus getSharedData() {
        return getDataObject("/lol/status/v3/shard-data", ShardStatus.class);
    }
}
