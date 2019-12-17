package wsg.lol.service.intf;

import org.apache.ibatis.session.RowBounds;
import wsg.lol.common.base.GenericResult;
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
     * Add a summoner by the id, including the masteries of champions and the entry of league.
     */
    Result addSummoner(String summonerName, String encryptUsername);

    /**
     * Update the information of the specified summoner, including the masteries of champions and the entry of league.
     */
    Result updateSummoner(String summonerId, String encryptUsername);

    /**
     * Update the last time updating the matches of the summoner specified by accountId.
     */
    Result updateSummonerLastMatch(String accountId, Date lastMatch);

    /**
     * Update the last time updating the summoner specified by summonerId.
     */
    Result updateSummonerLastUpdate(String summonerId, Date lastUpdate);

    /**
     * Get summoners that need to be updated.
     */
    ListResult<SummonerDto> getSummonersForUpdate(RowBounds rowBounds);

    /**
     * Get summoners whose matches need to be updated.
     */
    ListResult<SummonerDto> getSummonersForMatch(RowBounds rowBounds);

    /**
     * Get base info for the summoner of the specified name.
     */
    GenericResult<SummonerDto> getSummonersByName(String summonerName);
}
