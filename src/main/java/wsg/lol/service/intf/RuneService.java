package wsg.lol.service.intf;

import wsg.lol.common.base.Result;

/**
 * Service for runes.
 *
 * @author Kingen
 */
public interface RuneService {

    /**
     * Update the data of reforged runes once the version changes.
     */
    Result updateRunes(String version);
}
