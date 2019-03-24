package wsg.lol.service.action.intf;

import wsg.lol.pojo.base.BaseResult;
import wsg.lol.pojo.dmo.summoner.SummonerDmo;
import wsg.lol.pojo.enums.impl.code.TierEnum;
import wsg.lol.pojo.enums.impl.others.DivisionEnum;
import wsg.lol.pojo.enums.impl.others.PositionEnum;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-02-28 14:47
 */
public interface RealAction {

    /**
     * Build the basic lib of summoners by querying apex leagues and all positional leagues. Just add data of
     * summoners.
     * <p>
     * Once.
     */
    BaseResult buildBaseSummonerLibByLeague();

    /**
     * Build the basic lib of apex summoners.
     */
    BaseResult buildApexSummonerLib();

    /**
     * Build the lib of positional summoners with specified tier.
     */
    BaseResult buildPositionalSummonerLib(TierEnum tier, DivisionEnum division, PositionEnum position);

    /**
     * Extend the lib of summoners by querying the summoners in one's matches recursively. Add data of summoners and
     * their matches.
     * <p>
     * Scheduler.
     */
    BaseResult extendSummonerLibByMatch();

    /**
     * Update the information of leagues of all the summoners
     * <p>
     * Scheduler
     */
    BaseResult updateLeagues();

    /**
     * Update the data of a certain summoner.
     *
     * @param summonerDmo
     * @return
     */
    BaseResult updateSummonerData(SummonerDmo summonerDmo);
}
