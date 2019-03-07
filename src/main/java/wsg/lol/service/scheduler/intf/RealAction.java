package wsg.lol.service.scheduler.intf;

import wsg.lol.common.base.ResultDto;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-02-28 14:47
 */
public interface RealAction {

    /**
     * Build the basic lib of summoners by querying apex leagues and all positional leagues.
     * Just add data of summoners.
     * Once.
     */
    ResultDto buildBaseSummonerLibByLeague();

    /**
     * Extend the lib of summoners by querying the summoners in one's matches recursively.
     * Add data of summoners and their matches.
     * Scheduler.
     */
    ResultDto extendSummonerLibByMatch();

    /**
     * Update the league of each summoner.
     * Scheduler.
     */
    ResultDto updateLeague();
}
