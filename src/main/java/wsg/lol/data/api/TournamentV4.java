package wsg.lol.data.api;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import wsg.lol.common.annotation.AccessApi;
import wsg.lol.pojo.dto.api.tournament.PostTournamentCodeDto;
import wsg.lol.pojo.dto.api.tournament.QueryTournamentDto;

import java.util.List;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-01 14:02
 */
@Component
public class TournamentV4 extends BaseApi {

    /**
     * Create a tournament code for the given tournament.
     */
    @AccessApi
    public List<String> postCodes(QueryTournamentDto queryTournamentDto,
                                  PostTournamentCodeDto postTournamentCodeDto) {
        String jsonStr = postJSONString("/lol/tournament/v4/codes", queryTournamentDto, postTournamentCodeDto);
        return JSON.parseArray(jsonStr, String.class);
    }

    // wsg
}
