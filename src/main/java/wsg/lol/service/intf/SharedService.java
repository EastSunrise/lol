package wsg.lol.service.intf;

import wsg.lol.common.base.Result;

/**
 * Service for common data.
 *
 * @author Kingen
 */
public interface SharedService {

    /**
     * Update the data of shared status.
     */
    Result updateSharedStatus();

    /**
     * Update the rotation of champions.
     */
    Result updateChampionRotation();
}
