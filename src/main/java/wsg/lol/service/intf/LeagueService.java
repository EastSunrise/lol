package wsg.lol.service.intf;

import wsg.lol.common.base.Result;

/**
 * Service for leagues.
 *
 * @author Kingen
 */
public interface LeagueService {

    /**
     * Initialize the database by query the data of leagues each rank queue, tier and division.
     */
    Result initialByLeagues();
}
