package wsg.lol.service.intf;

import org.apache.ibatis.session.RowBounds;
import wsg.lol.common.pojo.dto.summoner.SummonerDto;

import java.util.Date;
import java.util.List;

/**
 * Service for summoners.
 *
 * @author Kingen
 */
public interface SummonerService {

    /**
     * Update the information of the specified summoner, including the masteries of champions and the entry of league.
     */
    void updateSummoner(String summonerId);

    /**
     * Add events of the matches of the specified summoner.
     */
    void updateMatches(String accountId, Date beginTime);

    /**
     * Get summoners that need to be updated.
     */
    List<SummonerDto> getSummonersForUpdate(RowBounds rowBounds);

    /**
     * Get summoners whose matches need to be updated.
     */
    List<SummonerDto> getSummonersForMatch(RowBounds rowBounds);

    /**
     * Get base info for the summoner of the specified name.
     */
    SummonerDto getSummonersByName(String summonerName);

    /**
     * Get base info for the summoner of the specified name.
     */
    SummonerDto getSummonersById(String summonerId);
}
