package wsg.lol.service.intf;

/**
 * Service for scheduled tasks.
 *
 * @author Kingen
 */
public interface ScheduleService {

    /**
     * Check the version. Update the static data from the dragon if it's not the latest version.
     */
    void checkVersion();

    /**
     * Update the shard status and the rotation of champions.
     */
    void updateSharedData();

    /**
     * Update the featured games.
     */
    void updateFeaturedGames();

    /**
     * Update the summoners with the most early update time.
     */
    void updateSummoners();

    /**
     * Add events of the matches after the last time updating the matches.
     */
    void updateMatches();
}
