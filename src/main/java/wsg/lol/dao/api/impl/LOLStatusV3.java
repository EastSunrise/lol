package wsg.lol.dao.api.impl;

import org.springframework.stereotype.Component;
import wsg.lol.common.annotation.AccessApi;
import wsg.lol.common.pojo.dto.share.ShardStatus;
import wsg.lol.dao.api.client.BaseApi;

/**
 * @author EastSunrise
 * <a href="https://developer.riotgames.com/apis#lol-status-v3">LOL-STATUS-V3</a>
 */
@Component
@AccessApi
public class LOLStatusV3 extends BaseApi {

    /**
     * Get League of Legends status for the given shard.
     * <p>
     * Requests to this API are not counted against the application Rate Limits.
     *
     * @see <a href="https://developer.riotgames.com/apis#lol-status-v3/GET_getShardData"/>
     */
    public ShardStatus getSharedData() {
        return this.getObject("/lol/status/v3/shard-data", ShardStatus.class);
    }
}
