package wsg.lol.service.user.intf;

import wsg.lol.pojo.dto.query.state.GetChampionDto;
import wsg.lol.pojo.dto.query.state.GetChampionListDto;
import wsg.lol.pojo.dto.state.ChampionDto;

import java.util.List;

/**
 * wsg
 *
 * @author wangsigen
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
}
