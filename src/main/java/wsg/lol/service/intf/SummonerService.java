package wsg.lol.service.intf;

import org.apache.ibatis.session.RowBounds;
import wsg.lol.common.base.ListResult;
import wsg.lol.common.base.Result;
import wsg.lol.common.pojo.dto.summoner.SummonerDto;

import java.util.Date;

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
    Result updateSummonerInfo(String summonerId);

    /**
     * Update the last time updating the matches of the summoner specified by accountId.
     */
    Result updateSummonerLastMatch(String accountId, Date lastMatch);

    /**
     * Update the champion masteries of the specified summoner.
     */
    Result updateChampionMasteries(String summonerId);
}
