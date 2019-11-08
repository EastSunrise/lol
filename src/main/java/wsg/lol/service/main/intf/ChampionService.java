package wsg.lol.service.main.intf;

import wsg.lol.common.pojo.base.BaseResult;
import wsg.lol.common.pojo.dto.query.state.GetChampionDto;
import wsg.lol.common.pojo.dto.query.state.GetChampionListDto;
import wsg.lol.common.pojo.dto.state.ChampionDto;

import java.util.List;

/**
 * wsg
 *
 * @author EastSunrise
 */
public interface ChampionService {

    /**
     * Query list of champions on the condition.
     */
    List<ChampionDto> getChampionList(GetChampionListDto getChampionListDto);

    /**
     * Query the detail of the specified champion.
     */
    ChampionDto getChampionInfo(GetChampionDto getChampionDto);

    /**
     * update the info of champions.
     */
    BaseResult updateChampions(List<ChampionDto> championDtoList);
}
