package wsg.lol.common.pojo.dto.champion;

import wsg.lol.common.base.BaseDto;
import wsg.lol.common.base.IJson;

import java.util.List;

/**
 * // TODO: (Kingen, 2019/11/18)
 *
 * @author EastSunrise
 */
public class ChampionRotationDto extends BaseDto implements IJson {

    private List<Integer> freeChampionIdsForNewPlayers;
    private List<Integer> freeChampionIds;
    private Integer maxNewPlayerLevel;

    public List<Integer> getFreeChampionIdsForNewPlayers() {
        return freeChampionIdsForNewPlayers;
    }

    public void setFreeChampionIdsForNewPlayers(List<Integer> freeChampionIdsForNewPlayers) {
        this.freeChampionIdsForNewPlayers = freeChampionIdsForNewPlayers;
    }

    public List<Integer> getFreeChampionIds() {
        return freeChampionIds;
    }

    public void setFreeChampionIds(List<Integer> freeChampionIds) {
        this.freeChampionIds = freeChampionIds;
    }

    public Integer getMaxNewPlayerLevel() {
        return maxNewPlayerLevel;
    }

    public void setMaxNewPlayerLevel(Integer maxNewPlayerLevel) {
        this.maxNewPlayerLevel = maxNewPlayerLevel;
    }
}
