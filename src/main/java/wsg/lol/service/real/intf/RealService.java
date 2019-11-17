package wsg.lol.service.real.intf;

import wsg.lol.common.enums.rank.DivisionEnum;
import wsg.lol.common.enums.rank.PositionEnum;
import wsg.lol.common.enums.rank.TierEnum;
import wsg.lol.common.result.base.Result;

/**
 * wsg
 *
 * @author EastSunrise
 */
public interface RealService {

    /**
     * Build the basic lib of summoners by querying apex leagues and all positional leagues. Just add data of
     * summoners.
     * <p>
     * Once.
     */
    Result buildBaseLib();

    /**
     * Build the basic lib of apex summoners.
     */
    Result buildApexSummonerLib();

    /**
     * Build the lib of positional summoners with specified tier.
     */
    @Deprecated
    Result buildPositionalSummonerLib(TierEnum tier, DivisionEnum division, PositionEnum position);

    /**
     * Update the data of summoners on the real time.
     */
    Result updateSummoners();

    /**
     * Update the data of a certain summoner.
     */
    Result updateSummonerById(String summonerId);

    /**
     * Extend the lib of summoners by querying the summoners in one's matches recursively. Add data of summoners and
     * their matches.
     * Base count each time.
     */
    Result extendLib();

    /**
     * Extend the data of a certain match reference.
     */
    Result updateMatchReference(Integer id);
}
