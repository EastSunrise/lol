package wsg.lol.service.intf;

import org.apache.ibatis.session.RowBounds;
import wsg.lol.common.base.ListResult;
import wsg.lol.common.base.Result;
import wsg.lol.common.pojo.dto.summoner.SummonerDto;

/**
 * Service for summoners.
 *
 * @author Kingen
 */
public interface SummonerService {

    /**
     * Get summoners that need to be updated.
     */
    ListResult<SummonerDto> getSummonersForUpdate(RowBounds rowBounds);

    /**
     * Get summoners whose matches need to be updated.
     */
    ListResult<SummonerDto> getSummonersForMatch(RowBounds rowBounds);

    /**
     * Update the base information of the specified summoner.
     */
    Result updateSummoner(String summonerId);
}
