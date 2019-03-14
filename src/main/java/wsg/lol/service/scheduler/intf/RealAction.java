package wsg.lol.service.scheduler.intf;

import wsg.lol.common.base.BaseResult;
import wsg.lol.common.enums.impl.name.TierEnum;
import wsg.lol.common.enums.impl.others.DivisionEnum;
import wsg.lol.common.enums.impl.others.PositionEnum;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-02-28 14:47
 */
public interface RealAction {

    /**
     * Build the basic lib of summoners by querying apex leagues and all positional leagues. Just add data of summoners.
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
     * their matches. Scheduler.
     */
    BaseResult extendSummonerLibByMatch();
}
