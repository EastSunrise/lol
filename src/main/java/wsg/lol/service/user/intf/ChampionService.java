package wsg.lol.service.user.intf;

import wsg.lol.pojo.dto.query.state.GetChampionDto;
import wsg.lol.pojo.dto.query.state.GetChampionListDto;
import wsg.lol.pojo.dto.state.champion.ChampionDto;

import java.util.List;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-02-26 17:16
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
