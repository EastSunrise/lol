package wsg.lol.common.pojo.dto.champion;

import wsg.lol.common.pojo.base.BaseDto;
import wsg.lol.common.pojo.base.IJson;

import java.util.List;

/**
 * @author EastSunrise
 */
public class ChampionRotationDto extends BaseDto implements IJson {

    private List<Integer> freeChampionIdsForNewPlayers;
    private List<Integer> freeChampionIds;
    private int maxNewPlayerLevel;

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

    public int getMaxNewPlayerLevel() {
        return maxNewPlayerLevel;
    }

    public void setMaxNewPlayerLevel(int maxNewPlayerLevel) {
        this.maxNewPlayerLevel = maxNewPlayerLevel;
    }
}
