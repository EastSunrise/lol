package wsg.lol.service.main.intf;

import wsg.lol.common.base.Result;
import wsg.lol.common.pojo.dto.champion.ChampionDto;

import java.util.List;

/**
 * wsg
 *
 * @author EastSunrise
 */
public interface ChampionService {

    /**
     * update the info of champions.
     */
    Result updateChampions(List<ChampionDto> championDtoList);
}
