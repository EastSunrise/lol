package wsg.lol.service.intf;

import wsg.lol.common.base.GenericResult;
import wsg.lol.common.pojo.dto.share.ChampionRotation;
import wsg.lol.common.pojo.dto.share.FeaturedGames;
import wsg.lol.common.pojo.dto.share.ShardStatus;

/**
 * Service for common data.
 *
 * @author Kingen
 */
public interface SharedService {

    /**
     * Evict the cache.
     */
    void updateFeaturedGames();

    /**
     * Get the featured games, from the cache first.
     */
    GenericResult<FeaturedGames> getFeaturedGames();

    /**
     * Evict the cache.
     */
    void updateShardStatus();

    /**
     * Get the shard status, from the cache first.
     */
    GenericResult<ShardStatus> getShardStatus();

    /**
     * Evict the cache.
     */
    void updateChampionRotation();

    /**
     * Get the rotation of champions, from the cache first.
     */
    GenericResult<ChampionRotation> getChampionRotation();
}
