package wsg.lol.dao.api.impl;

import org.springframework.stereotype.Component;
import wsg.lol.common.base.AppException;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.common.pojo.dto.match.MatchDto;
import wsg.lol.common.pojo.dto.match.MatchExtDto;
import wsg.lol.common.pojo.dto.match.MatchListDto;
import wsg.lol.common.pojo.dto.match.MatchTimelineDto;
import wsg.lol.common.pojo.query.QueryMatchListDto;
import wsg.lol.dao.api.client.BaseApi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kingen
 * @see <a href="https://developer.riotgames.com/apis#match-v4">MATCH-V4</a>
 */
@Component
public class MatchV4 extends BaseApi {

    /**
     * Get match list for games played on given account ID and platform ID and filtered using given filter parameters, if any.
     * <p>
     * IMPLEMENTATION NOTES
     * A number of optional parameters are provided for filtering. It is up to the caller to ensure
     * that the combination of filter parameters provided is valid for the requested account,
     * otherwise, no matches may be returned. If beginIndex is specified, but not endIndex,
     * then endIndex defaults to beginIndex+100. If endIndex is specified, but not beginIndex,
     * then beginIndex defaults to 0. If both are specified, then endIndex must be greater
     * than beginIndex. The maximum range allowed is 100, otherwise a 400 error code is returned.
     * If beginTime is specified, but not endTime, then endTime defaults to the the current unix
     * timestamp in milliseconds (the maximum time range limitation is not observed in this specific
     * case). If endTime is specified, but not beginTime, then beginTime defaults to the start of
     * the account's match history returning a 400 due to the maximum time range limitation.
     * If both are specified, then endTime should be greater than beginTime. The maximum time
     * range allowed is one week, otherwise a 400 error code is returned.
     *
     * @see <a href="https://developer.riotgames.com/apis#match-v4/GET_getMatchlist"/>
     */
    public MatchListDto getMatchListByAccount(String accountId, QueryMatchListDto queryMatchListDto) {
        if (!queryMatchListDto.isValid()) {
            throw new AppException(ErrorCodeConst.ILLEGAL_ARGS);
        }

        Map<String, Object> params = new HashMap<>();
        params.put("encryptedAccountId", accountId);
        return this.getObject("/lol/match/v4/matchlists/by-account/{encryptedAccountId}", params, queryMatchListDto, MatchListDto.class);
    }

    /**
     * Get match by match ID.
     *
     * @see <a href="https://developer.riotgames.com/apis#match-v4/GET_getMatch"/>
     */
    public MatchExtDto getMatchById(long matchId) {
        Map<String, Object> params = new HashMap<>();
        params.put("matchId", matchId);
        return this.getObject("/lol/match/v4/matches/{matchId}", params, MatchExtDto.class);
    }

    /**
     * Get match timeline by match ID.
     * <p>
     * IMPLEMENTATION NOTES
     * Not all matches have timeline data.
     *
     * @see <a href="https://developer.riotgames.com/apis#match-v4/GET_getMatchTimeline"/>
     */
    public MatchTimelineDto getTimelineByMatchId(long matchId) {
        Map<String, Object> params = new HashMap<>();
        params.put("matchId", matchId);
        return this.getObject("/lol/match/v4/timelines/by-match/{matchId}", params, MatchTimelineDto.class);
    }

    /**
     * Get match IDs by tournament code.
     *
     * @see <a href="https://developer.riotgames.com/apis#match-v4/GET_getMatchIdsByTournamentCode"/>
     */
    public List<Long> getMatchesByTournamentCode(String tournamentCode) {
        Map<String, Object> params = new HashMap<>();
        params.put("tournamentCode", tournamentCode);
        return this.getArray("/lol/match/v4/match/by-tournament-code/{tournamentCode}/ids", params, Long.class);
    }

    /**
     * Get match by match ID and tournament code.
     *
     * @see <a href="https://developer.riotgames.com/apis#match-v4/GET_getMatchByTournamentCode"/>
     */
    public MatchDto getMatchesByIdAndTournamentCode(long matchId, String tournamentCode) {
        Map<String, Object> params = new HashMap<>();
        params.put("tournamentCode", tournamentCode);
        params.put("matchId", matchId);
        return this.getObject("/lol/match/v4/match/{matchId}/by-tournament-code/{tournamentCode}", params, MatchDto.class);
    }
}
