package wsg.lol.service.scheduler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wsg.lol.common.base.BaseResult;
import wsg.lol.common.utils.LogUtil;
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
    public BaseResult buildChampionLib() {
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
            return BaseResult.fail("");

        if (skinDmoList.size() != championMapper.batchInsertSkins(skinDmoList))
            LogUtil.info("");

        if (spellDmoList.size() != championMapper.batchInsertSpells(spellDmoList))
            LogUtil.info("");

        if (tipDmoList.size() != championMapper.batchInsertTips(tipDmoList))
            LogUtil.info("");

        return BaseResult.success();
    }
}
