package wsg.lol.service.scheduler.impl;

import wsg.lol.common.base.ResultDto;
import wsg.lol.data.file.ChampionFile;
import wsg.lol.dto.state.champion.ChampionExtDto;
import wsg.lol.service.scheduler.intf.VersionAction;

import java.util.List;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-02-26 16:16
 */
public class VersionActionImpl implements VersionAction {

    @Override
    public ResultDto buildChampions() {
        List<ChampionExtDto> championExtDtoList = ChampionFile.getChampions();
        return ResultDto.success();
    }
}
