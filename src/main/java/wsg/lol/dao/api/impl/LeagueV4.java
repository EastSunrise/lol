package wsg.lol.dao.api.impl;

import org.springframework.stereotype.Component;
import wsg.lol.common.annotation.AccessApi;
import wsg.lol.dao.api.base.BaseApi;
import wsg.lol.pojo.dmo.league.PositionDmo;
import wsg.lol.pojo.dto.api.league.LeagueExtDto;
import wsg.lol.pojo.enums.impl.code.TierEnum;
import wsg.lol.pojo.enums.impl.others.DivisionEnum;
import wsg.lol.pojo.enums.impl.others.PositionEnum;
import wsg.lol.pojo.enums.impl.others.RankQueueEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author King
 * @date 2019/2/12
 */
@Component
public class LeagueV4 extends BaseApi {

    /**
     * Get the apex league for given queue and tier.
     */
    @AccessApi
    public LeagueExtDto getApexLeagueByQueue(RankQueueEnum queue, TierEnum tier) {
        Map<String, Object> params = new HashMap<>();
        params.put("queue", queue);
        return getDataExtObject("/lol/league/v4/" + tier.name().toLowerCase() + "leagues/by-queue/{queue}",
                params, LeagueExtDto.class);
    }

    /**
     * Get league with given ID, including inactive entries.
     */
    @AccessApi
    public LeagueExtDto getLeagueById(String leagueId) {
        Map<String, Object> params = new HashMap<>();
        params.put("leagueId", leagueId);
        return getDataExtObject("/lol/league/v4/leagues/{leagueId}", params, LeagueExtDto.class);
    }

    /**
     * Get the queues that have positional ranks enabled.
     */
    @AccessApi
    public List<String> getPositionalRankQueues() {
        return getDataArray("/lol/league/v4/positional-rank-queues", String.class);
    }

    /**
     * Get league positions in all queues for a given summoner ID.
     */
    @AccessApi
    public List<PositionDmo> getLeaguePositionsBySummonerId(String summonerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("encryptedSummonerId", summonerId);
        return getDataArray("/lol/league/v4/positions/by-summoner/{encryptedSummonerId}", params, PositionDmo.class);
    }

    /**
     * Get all the positional league entries.
     *
     * @param queue    positional value
     * @param tier     positional value
     * @param position positional value
     */
    @AccessApi
    public List<PositionDmo> getAllPositionLeagues(RankQueueEnum queue, TierEnum tier, DivisionEnum division,
                                                   PositionEnum position, int page) {
        Map<String, Object> params = new HashMap<>();
        params.put("positionalQueue", queue);
        params.put("tier", tier);
        params.put("division", division);
        params.put("position", position);
        params.put("page", page);
        return getDataArray("/lol/league/v4/positions/{positionalQueue}/{tier}/{division}/{position}/{page" +
                "}", params, PositionDmo.class);
    }
}
