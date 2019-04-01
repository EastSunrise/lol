package wsg.lol.pojo.dto.api.champion;

import wsg.lol.pojo.base.BaseDto;
import wsg.lol.pojo.base.IJson;

import java.util.List;

/**
 * @author King
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
