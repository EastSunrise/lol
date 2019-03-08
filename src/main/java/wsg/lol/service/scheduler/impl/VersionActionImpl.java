package wsg.lol.service.scheduler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wsg.lol.common.base.ResultDto;
import wsg.lol.dao.mapper.ChampionMapper;
import wsg.lol.data.file.ChampionFile;
import wsg.lol.dmo.champion.ChampionDmo;
import wsg.lol.dmo.champion.SkinDmo;
import wsg.lol.dmo.champion.SpellDmo;
import wsg.lol.dmo.champion.TipDmo;
import wsg.lol.dto.state.champion.ChampionExtDto;
import wsg.lol.service.scheduler.intf.VersionAction;

import java.util.LinkedList;
import java.util.List;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-02-26 16:16
 */
@Service("versionAction")
public class VersionActionImpl implements VersionAction {

    @Autowired
    private ChampionMapper championMapper;

    @Override
    public ResultDto buildChampionLib() {
        List<ChampionExtDto> championExtDtoList = ChampionFile.getChampions();
        List<ChampionDmo> championDmoList = new LinkedList<>();
        List<SkinDmo> skinDmoList = new LinkedList<>();
        List<SpellDmo> spellDmoList = new LinkedList<>();
        List<TipDmo> tipDmoList = new LinkedList<>();
        for (ChampionExtDto championExtDto : championExtDtoList) {
            championDmoList.add(championExtDto.getChampionDmo());
            skinDmoList.addAll(championExtDto.getSkinDmoList());
            spellDmoList.addAll(championExtDto.getSpellDmoList());
            spellDmoList.add(championExtDto.getPassive());
            tipDmoList.addAll(championExtDto.getTipDmoList());
        }

        if (championDmoList.size() != championMapper.batchInsertChampions(championDmoList))
            return ResultDto.fail("");

        if (skinDmoList.size() != championMapper.batchInsertSkins(skinDmoList))
            System.out.println("");

        if (spellDmoList.size() != championMapper.batchInsertSpells(spellDmoList))
            System.out.println("");

        if (tipDmoList.size() != championMapper.batchInsertTips(tipDmoList))
            System.out.println("");

        return ResultDto.success();
    }
}
