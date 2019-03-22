package wsg.lol.service.service.intf;

import wsg.lol.pojo.dto.query.GetChampionDto;
import wsg.lol.pojo.dto.query.GetChampionListDto;
import wsg.lol.pojo.dto.result.ChampionListResult;
import wsg.lol.pojo.dto.state.champion.ChampionExtDto;

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
    ChampionListResult getChampionList(GetChampionListDto getChampionListDto);

    /**
     * Query the detail of the specified champion.
     */
    ChampionExtDto getChampionInfo(GetChampionDto getChampionDto);
}
