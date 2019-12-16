package wsg.lol.service.intf;

import wsg.lol.common.base.GenericResult;
import wsg.lol.common.base.Result;
import wsg.lol.common.enums.share.ImageGroupEnum;
import wsg.lol.common.pojo.dto.item.ImageDto;
import wsg.lol.common.pojo.dto.share.ChampionRotation;
import wsg.lol.common.pojo.dto.share.FeaturedGames;
import wsg.lol.common.pojo.dto.share.ShardStatus;

import java.util.List;

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

    /**
     * Update images of specified groups.
     */
    Result updateImages(List<ImageDto> images, ImageGroupEnum... groups);

    /**
     * Update the data of maps once the version changes.
     */
    Result updateMaps(String version);

    /**
     * Update the data of profile icons once the version changes.
     */
    Result updateProfileIcons(String version);
}
