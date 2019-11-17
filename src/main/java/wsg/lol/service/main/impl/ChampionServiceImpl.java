package wsg.lol.service.main.impl;

import org.springframework.stereotype.Service;
import wsg.lol.common.pojo.dto.champion.ChampionDto;
import wsg.lol.common.result.base.Result;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.service.main.intf.ChampionService;

import java.util.List;

/**
 * wsg
 *
 * @author EastSunrise
 */
@Service("championService")
public class ChampionServiceImpl implements ChampionService {

    @Override
    public Result updateChampions(List<ChampionDto> championDtoList) {
        // TODO: (wangsigen, 2019/11/8) update champions and extra info
        return ResultUtils.success();
    }
}
