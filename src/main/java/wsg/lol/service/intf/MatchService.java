package wsg.lol.service.intf;

import wsg.lol.common.base.Result;

import java.util.Date;

/**
 * Service for matches.
 *
 * @author Kingen
 */
public interface MatchService {

    /**
     * Add events of the matches of the summoner specified by {@param summonerId}
     */
    Result updateMatches(String accountId, Date beginTime);
}
