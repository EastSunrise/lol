package wsg.lol.service.intf;

/**
 * Service for champions.
 *
 * @author Kingen
 */
public interface ChampionService {

    /**
     * Update the data of champions once the version changes.
     */
    void updateChampions(String version);

    /**
     * Update the data of summoner spells once the version changes.
     */
    void updateSummonerSpells(String version);
}
