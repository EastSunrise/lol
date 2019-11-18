package wsg.lol.dao.dragon.intf;

import java.util.List;

/**
 * General data interface.
 *
 * @author Kingen
 * @see <a href="https://developer.riotgames.com/docs/lol#data-dragon_realms-versions">Realms & Versions</a>
 */
public interface GeneralDao {

    /**
     * Get all the valid versions.
     *
     * @see <a href="https://ddragon.leagueoflegends.com/api/versions.json">versions.json</a>
     */
    List<String> getVersions();

    /**
     * Get the latest version.
     */
    String getLatestVersion();
}
