package wsg.lol.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import wsg.lol.common.base.GenericResult;
import wsg.lol.common.pojo.dto.share.ChampionRotation;
import wsg.lol.common.pojo.dto.share.FeaturedGames;
import wsg.lol.common.pojo.dto.share.ShardStatus;
import wsg.lol.dao.api.impl.ChampionV3;
import wsg.lol.dao.api.impl.LOLStatusV3;
import wsg.lol.dao.api.impl.SpectatorV4;
import wsg.lol.service.intf.SharedService;

/**
 * @author Kingen
 */
@Service
public class SharedServiceImpl implements SharedService {

    private static final Logger logger = LoggerFactory.getLogger(SharedService.class);

    private SpectatorV4 spectatorV4;

    private LOLStatusV3 lolStatusV3;

    private ChampionV3 championV3;

    @CacheEvict(cacheNames = "shared", key = "'featuredGames'")
    public void updateFeaturedGames() {
        logger.info("Evict the cache of the featured games.");
    }

    @Cacheable(cacheNames = "shared", key = "'featuredGames'")
    public GenericResult<FeaturedGames> getFeaturedGames() {
        return GenericResult.create(spectatorV4.getFeaturedGames());
    }

    @CacheEvict(cacheNames = "shared", key = "'shardStatus'")
    public void updateShardStatus() {
        logger.info("Evict the cache of the shared status.");
    }

    @Cacheable(cacheNames = "shared", key = "'shardStatus'")
    public GenericResult<ShardStatus> getShardStatus() {
        return GenericResult.create(lolStatusV3.getSharedData());
    }

    @CacheEvict(cacheNames = "shared", key = "'championRotation'")
    public void updateChampionRotation() {
        logger.info("Evict the cache of the rotation of champions.");
    }

    @Cacheable(cacheNames = "shared", key = "'championRotation'")
    public GenericResult<ChampionRotation> getChampionRotation() {
        return GenericResult.create(championV3.getChampionRotation());
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
