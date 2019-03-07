package wsg.lol.dto.state.champion;

import wsg.lol.common.base.BaseDto;

import java.util.List;

/**
 * @author King
 * @date 2019/2/11
 */
public class ChampionRotationDto extends BaseDto {

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
