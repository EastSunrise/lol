package wsg.lol.data.api;

import com.alibaba.fastjson.JSON;
import wsg.lol.dto.api.tournament.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author King
 * @date 2019/2/12
 */
public class TournamentStubV4 extends BaseApi {

    /**
     * Create a mock tournament code for the given tournament.
     */
    public static List<String> postCodes(QueryTournamentDto queryTournamentDto,
                                         PostTournamentCodeDto postTournamentCodeDto) {
        String jsonStr = postJSONString("/lol/tournament-stub/v4/codes", queryTournamentDto, postTournamentCodeDto);
        return JSON.parseArray(jsonStr, String.class);
    }

    /**
     * Gets a mock list of lobby events by tournament code.
     */
    public static LobbyEventDtoWrapper getLobbyEventsByCode(String tournamentCode) {
        Map<String, Object> params = new HashMap<>();
        params.put("tournamentCode", tournamentCode);
        return getCommonDataObject("/lol/tournament-stub/v4/lobby-events/by-code/{tournamentCode}", params,
                LobbyEventDtoWrapper.class);
    }

    /**
     * Creates a mock tournament provider and returns its ID.
     * <p>
     * IMPLEMENTATION NOTES
     * Providers will need to call this endpoint first to register their callback URL and their API key with the
     * tournament system before any other tournament provider endpoints will work.
     */
    public static int postProviders(PostProviderRegistrationDto postProviderRegistrationDto) {
        String jsonStr = postJSONString("/lol/tournament-stub/v4/providers", postProviderRegistrationDto);
        return JSON.parseObject(jsonStr, Integer.class);
    }

    /**
     * Creates a mock tournament and returns its ID.
     */
    public static int postTournaments(PostTournamentRegistrationDto postTournamentRegistrationDto) {
        String jsonStr = postJSONString("/lol/tournament-stub/v4/tournaments", postTournamentRegistrationDto);
        return JSON.parseObject(jsonStr, Integer.class);
    }
}
