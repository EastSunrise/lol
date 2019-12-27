package wsg.lol.dao.api.impl;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import wsg.lol.common.base.AppException;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.common.enums.share.RankQueueEnum;
import wsg.lol.common.enums.summoner.DivisionEnum;
import wsg.lol.common.enums.summoner.TierEnum;
import wsg.lol.common.pojo.dto.league.LeagueListDto;
import wsg.lol.common.pojo.dto.summoner.LeagueEntryDto;
import wsg.lol.dao.api.client.BaseApi;
import wsg.lol.service.aop.Performance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kingen
 * @see <a href="https://developer.riotgames.com/apis#league-v4">LEAGUE-V4</a>
 */
@Component
@Performance
public class LeagueV4 extends BaseApi {

    private static final Logger logger = LoggerFactory.getLogger(LeagueV4.class);

    /**
     * Get league entries in all queues for a given summoner ID.
     *
     * @see <a href="https://developer.riotgames.com/apis#league-v4/GET_getLeagueEntriesForSummoner"/>
     */
    public List<LeagueEntryDto> getLeagueEntriesBySummonerId(String summonerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("encryptedSummonerId", summonerId);
        return this.getArray("/lol/league/v4/entries/by-summoner/{encryptedSummonerId}", params, LeagueEntryDto.class);
    }

    /**
     * Get all the league entries.
     *
     * @see <a href="https://developer.riotgames.com/apis#league-exp-v4/GET_getLeagueEntries"/>
     */
    public List<LeagueEntryDto> getLeagueEntriesExp(RankQueueEnum queue, TierEnum tier, DivisionEnum division, int page) {
        if (!ArrayUtils.contains(TierEnum.RANKED_TIERS, tier) || !division.isValidDivision(tier)) {
            logger.error("Unmatched tier {} and division {}.", tier, division);
            throw new AppException(ErrorCodeConst.ILLEGAL_ARGS, "Unmatched tier and division.");
        }

        if (page < 1) {
            logger.error("Page should start with 1.");
            throw new AppException(ErrorCodeConst.ILLEGAL_ARGS, "Page should start with 1.");
        }

        Map<String, Object> params = new HashMap<>();
        params.put("queue", queue);
        params.put("tier", tier);
        params.put("division", division);
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("page", page);
        return this.getArray("/lol/league-exp/v4/entries/{queue}/{tier}/{division}", params, queryParams, LeagueEntryDto.class);
    }

    /**
     * Get all the league entries.
     *
     * @see <a href="https://developer.riotgames.com/apis#league-v4/GET_getLeagueEntries"/>
     */
    @Deprecated
    public List<LeagueEntryDto> getLeagueEntries(RankQueueEnum queue, TierEnum tier, DivisionEnum division, int page) {
        Map<String, Object> params = new HashMap<>();
        params.put("positionalQueue", queue);
        params.put("tier", tier);
        params.put("division", division);
        params.put("page", page);
        return this.getArray("/lol/league/v4/entries/{queue}/{tier}/{division}", params, LeagueEntryDto.class);
    }

    /**
     * Get the apex league for given queue and tier.
     *
     * @see <a href="https://developer.riotgames.com/apis#league-v4/GET_getChallengerLeague"/>
     * @see <a href="https://developer.riotgames.com/apis#league-v4/GET_getGrandmasterLeague"/>
     * @see <a href="https://developer.riotgames.com/apis#league-v4/GET_getMasterLeague"/>
     */
    @Deprecated
    public LeagueListDto getApexLeagueByQueue(RankQueueEnum queue, TierEnum tier) {
        Map<String, Object> params = new HashMap<>();
        params.put("queue", queue);
        return this.getObject("/lol/league/v4/" + tier.name().toLowerCase() + "leagues/by-queue/{queue}", params, LeagueListDto.class);
    }

    /**
     * Get league with given ID, including inactive entries.
     *
     * @see <a href="https://developer.riotgames.com/apis#league-v4/GET_getLeagueById"/>
     */
    @Deprecated
    public LeagueListDto getLeagueById(String leagueId) {
        Map<String, Object> params = new HashMap<>();
        params.put("leagueId", leagueId);
        return this.getObject("/lol/league/v4/leagues/{leagueId}", params, LeagueListDto.class);
    }
}
