package wsg.lol.service.real.intf;

import wsg.lol.pojo.base.BaseResult;
import wsg.lol.pojo.dmo.summoner.SummonerDmo;
import wsg.lol.pojo.enums.impl.code.TierEnum;
import wsg.lol.pojo.enums.impl.others.DivisionEnum;
import wsg.lol.pojo.enums.impl.others.PositionEnum;

/**
 * wsg
 *
 * @author wangsigen
 */
public interface RealService {

    /**
     * Build the basic lib of summoners by querying apex leagues and all positional leagues. Just add data of
     * summoners.
     * <p>
     * Once.
     */
    BaseResult buildBaseSummonerLib();

    /**
     * Build the basic lib of apex summoners.
     */
    BaseResult buildApexSummonerLib();

    /**
     * Build the lib of positional summoners with specified tier.
     */
    BaseResult buildPositionalSummonerLib(TierEnum tier, DivisionEnum division, PositionEnum position);

    /**
     * Update the information of leagues of all the summoners
     * <p>
     * Scheduler
     */
    BaseResult updateLeagues();

    /**
     * Extend the lib of summoners by querying the summoners in one's matches recursively. Add data of summoners and
     * their matches.
     * <p>
     * Scheduler.
     */
    BaseResult extendSummonerLibByMatch();

    /**
     * Update the data of a certain summoner.
     */
    BaseResult updateSummonerData(SummonerDmo summonerDmo);
}
