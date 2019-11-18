package wsg.lol.dao.api.impl;

import org.springframework.stereotype.Component;
import wsg.lol.common.enums.rank.DivisionEnum;
import wsg.lol.common.enums.rank.PositionEnum;
import wsg.lol.common.enums.rank.RankQueueEnum;
import wsg.lol.common.enums.rank.TierEnum;
import wsg.lol.common.pojo.dto.league.LeagueEntryDto;
import wsg.lol.common.pojo.dto.league.LeagueListDto;
import wsg.lol.dao.api.client.BaseApi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kingen
 * @see <a href="https://developer.riotgames.com/apis#league-v4">LEAGUE-V4</a>
 */
@Component
public class LeagueV4 extends BaseApi {

    /**
     * Get the apex league for given queue and tier.
     *
     * @see <a href="https://developer.riotgames.com/apis#league-v4/GET_getChallengerLeague"/>
     * @see <a href="https://developer.riotgames.com/apis#league-v4/GET_getGrandmasterLeague"/>
     * @see <a href="https://developer.riotgames.com/apis#league-v4/GET_getMasterLeague"/>
     */
    public LeagueListDto getApexLeagueByQueue(RankQueueEnum queue, TierEnum tier) {
        Map<String, Object> params = new HashMap<>();
        params.put("queue", queue);
        return this.getObject("/lol/league/v4/" + tier.name().toLowerCase() + "leagues/by-queue/{queue}", params, LeagueListDto.class);
    }

    /**
     * Get league entries in all queues for a given summoner ID.
     *
     * @see <a href="https://developer.riotgames.com/apis#league-v4/GET_getLeagueEntriesForSummoner"/>
     */
    public List<LeagueEntryDto> getLeaguePositionsBySummonerId(String summonerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("encryptedSummonerId", summonerId);
        return this.getArray("/lol/league/v4/positions/by-summoner/{encryptedSummonerId}", params, LeagueEntryDto.class);
    }

    /**
     * Get all the league entries.
     *
     * @param queue    positional value
     * @param tier     positional value
     * @param position positional value
     * @see <a href="https://developer.riotgames.com/apis#league-v4/GET_getLeagueEntries"/>
     */
    public List<LeagueEntryDto> getAllPositionLeagues(RankQueueEnum queue, TierEnum tier, DivisionEnum division, PositionEnum position, int page) {
        Map<String, Object> params = new HashMap<>();
        params.put("positionalQueue", queue);
        params.put("tier", tier);
        params.put("division", division);
        params.put("position", position);
        params.put("page", page);
        return this.getArray("/lol/league/v4/positions/{positionalQueue}/{tier}/{division}/{position}/{page}", params, LeagueEntryDto.class);
    }

    /**
     * Get league with given ID, including inactive entries.
     *
     * @see <a href="https://developer.riotgames.com/apis#league-v4/GET_getLeagueById"/>
     */
    public LeagueListDto getLeagueById(String leagueId) {
        Map<String, Object> params = new HashMap<>();
        params.put("leagueId", leagueId);
        return this.getObject("/lol/league/v4/leagues/{leagueId}", params, LeagueListDto.class);
    }
}
