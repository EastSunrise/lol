package wsg.lol.service.action.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wsg.lol.common.utils.LogUtil;
import wsg.lol.dao.mongo.intf.ChampionDao;
import wsg.lol.dao.mybatis.mapper.ChampionMapper;
import wsg.lol.dao.redis.intf.RedisStringDao;
import wsg.lol.dao.state.ChampionFile;
import wsg.lol.pojo.base.BaseResult;
import wsg.lol.pojo.dmo.champion.ChampionDmo;
import wsg.lol.pojo.dmo.champion.SkinDmo;
import wsg.lol.pojo.dmo.champion.SpellDmo;
import wsg.lol.pojo.dto.state.champion.ChampionExtDto;
import wsg.lol.service.action.intf.VersionAction;

import java.util.LinkedList;
import java.util.List;

/**
 * wsg
 *
 * @author wangsigen
 */
@Service("versionAction")
public class VersionActionImpl implements VersionAction {

    @Autowired
    private ChampionMapper championMapper;

    @Autowired
    private ChampionFile championFile;

    @Autowired
    private RedisStringDao redisStringDao;

    @Autowired
    private ChampionDao championDao;

    @Override
    public BaseResult updateChampionLib() {
        LogUtil.info("Start to update the data of champions.");
        List<ChampionExtDto> championExtDtoList = championFile.getChampions();
        List<ChampionDmo> championDmoList = new LinkedList<>();
        List<SkinDmo> skinDmoList = new LinkedList<>();
        List<SpellDmo> spellDmoList = new LinkedList<>();
        for (ChampionExtDto championExtDto : championExtDtoList) {
            championDmoList.add(championExtDto.getChampionDmo());
            skinDmoList.addAll(championExtDto.getSkinDmoList());
            spellDmoList.addAll(championExtDto.getSpellDmoList());
            spellDmoList.add(championExtDto.getPassive());
        }

        if (championDmoList.size() != championMapper.batchInsertOrUpdateChampions(championDmoList))
            return BaseResult.fail("");

        if (skinDmoList.size() != championMapper.batchInsertSkins(skinDmoList))
            LogUtil.info("");

        if (spellDmoList.size() != championMapper.batchInsertSpells(spellDmoList))
            LogUtil.info("");

        return BaseResult.success();
    }

    @Override
    public BaseResult updateItemLib() {
        LogUtil.info(championDao.getUser("wsg").getAddress().getFull());
        return BaseResult.success();
    }
}
