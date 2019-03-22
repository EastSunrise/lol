package wsg.lol.service.action.intf;

import wsg.lol.pojo.base.BaseResult;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-02-26 16:19
 */
public interface VersionAction {

    /**
     * Update the data of champions once the version changes.
     */
    BaseResult updateChampionLib();

    /**
     * Update the data of items once the version changes.
     */
    BaseResult updateItemLib();
}
