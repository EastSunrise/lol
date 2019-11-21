package wsg.lol.service.intf;

import wsg.lol.common.base.Result;

/**
 * Service for items.
 *
 * @author Kingen
 */
public interface ItemService {

    /**
     * Update the data of items once the version changes.
     */
    Result updateItems(String version);
}
