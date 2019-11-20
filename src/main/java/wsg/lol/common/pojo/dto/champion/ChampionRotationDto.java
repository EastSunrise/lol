package wsg.lol.common.pojo.dto.champion;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

import java.util.List;

/**
 * Bean for champion rotation.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ChampionRotationDto extends BaseDto {

    private List<Integer> freeChampionIdsForNewPlayers;
    private List<Integer> freeChampionIds;
    private Integer maxNewPlayerLevel;
}
