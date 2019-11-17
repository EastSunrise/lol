package wsg.lol.service.main.intf;

import wsg.lol.common.pojo.dto.champion.ChampionDto;
import wsg.lol.common.result.base.Result;

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
