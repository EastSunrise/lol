package wsg.lol.service.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;
import wsg.lol.common.base.GenericResult;
import wsg.lol.common.pojo.dto.champion.ChampionRotation;
import wsg.lol.common.pojo.dto.share.ShardStatus;
import wsg.lol.common.pojo.dto.spectator.FeaturedGames;
import wsg.lol.dao.api.impl.ChampionV3;
import wsg.lol.dao.api.impl.LOLStatusV3;
import wsg.lol.dao.api.impl.SpectatorV4;

/**
 * Shared data.
 *
 * @author Kingen
 */
@Configuration
public class SharedContext {

    private static final Logger logger = LoggerFactory.getLogger(SharedContext.class);

    private static final String FEATURED_GAMES = "FEATURED_GAMES";
    private static final String SHARD_STATUS = "SHARD_STATUS";
    private static final String CHAMPION_ROTATION = "CHAMPION_ROTATION";

    private SpectatorV4 spectatorV4;

    private LOLStatusV3 lolStatusV3;

    private ChampionV3 championV3;

    @CacheEvict(cacheNames = "shared", key = "'featuredGames'")
    public void updateFeaturedGames() {
        logger.info("Update the featured games.");
    }

    /**
     * Get the featured games, from the cache first.
     */
    @Cacheable(cacheNames = "shared", key = "'featuredGames'")
    public FeaturedGames getFeaturedGames() {
        return spectatorV4.getFeaturedGames();
    }

    @CacheEvict(cacheNames = "shared", key = "'shardStatus'")
    public void updateShardStatus() {
        logger.info("Update the shared status.");
    }

    /**
     * Get the shard status, from the cache first.
     */
    @Cacheable(cacheNames = "shared", key = "'shardStatus'")
    public GenericResult<ShardStatus> getShardStatus() {
        GenericResult<ShardStatus> result = new GenericResult<>();
        result.setObject(lolStatusV3.getSharedData());
        return result;
    }

    @CacheEvict(cacheNames = "shared", key = "'championRotation'")
    public void updateChampionRotation() {
        logger.info("Update the rotation of champions.");
    }

    /**
     * Get the rotation of champions, from the cache first.
     */
    @Cacheable(cacheNames = "shared", key = "'championRotation'")
    public GenericResult<ChampionRotation> getChampionRotation() {
        GenericResult<ChampionRotation> result = new GenericResult<>();
        result.setObject(championV3.getChampionRotation());
        return result;
    }

    @Autowired
    public void setChampionV3(ChampionV3 championV3) {
        this.championV3 = championV3;
    }

    @Autowired
    public void setLolStatusV3(LOLStatusV3 lolStatusV3) {
        this.lolStatusV3 = lolStatusV3;
    }

    @Autowired
    public void setSpectatorV4(SpectatorV4 spectatorV4) {
        this.spectatorV4 = spectatorV4;
    }
}
