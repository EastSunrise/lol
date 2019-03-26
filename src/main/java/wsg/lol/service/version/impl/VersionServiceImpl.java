package wsg.lol.service.version.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wsg.lol.common.utils.LogUtil;
import wsg.lol.dao.mongo.intf.MongoDao;
import wsg.lol.dao.state.intf.StateDao;
import wsg.lol.pojo.base.BaseResult;
import wsg.lol.pojo.base.StateBean;
import wsg.lol.pojo.dto.state.champion.ChampionDto;
import wsg.lol.pojo.dto.state.item.GroupDto;
import wsg.lol.pojo.dto.state.item.ItemDto;
import wsg.lol.pojo.dto.state.item.ItemExtDto;
import wsg.lol.pojo.dto.state.item.TreeDto;
import wsg.lol.pojo.dto.state.others.MapDto;
import wsg.lol.pojo.dto.state.rune.RuneTreeDto;
import wsg.lol.pojo.dto.state.spell.SummonerSpellDto;
import wsg.lol.service.version.intf.VersionService;

import java.util.LinkedList;
import java.util.List;

/**
 * wsg
 *
 * @author wangsigen
 */
@Service("versionAction")
public class VersionServiceImpl implements VersionService {

    private StateDao stateDao;

    private MongoDao mongoDao;

    @Override
    public BaseResult initLib() {
        LogUtil.info("Start to init the lib.");
        List<Class<? extends StateBean>> classes = new LinkedList<>();
        classes.add(ChampionDto.class);
        classes.add(ItemDto.class);
        classes.add(GroupDto.class);
        classes.add(TreeDto.class);
        classes.add(RuneTreeDto.class);
        classes.add(MapDto.class);
        classes.add(SummonerSpellDto.class);

        for (Class<? extends StateBean> clazz : classes) {
            mongoDao.dropCollection(clazz);
        }

        return BaseResult.success();
    }

    @Override
    public BaseResult updateLib() {
        updateChampionLib();
        updateItemLib();
        updateMapLib();
        updateRuneLib();
        updateSummonerSpellLib();
        return BaseResult.success();
    }

    @Override
    public BaseResult updateChampionLib() {
        LogUtil.info("Start to update the data of champions.");
        mongoDao.insertDocuments(stateDao.readChampions());
        return BaseResult.success();
    }

    @Override
    public BaseResult updateItemLib() {
        LogUtil.info("Start to update the data of items.");
        ItemExtDto itemExtDto = stateDao.readItems();
        mongoDao.insertDocuments(itemExtDto.getItemDtoList());
        mongoDao.insertDocuments(itemExtDto.getGroupDtoList());
        mongoDao.insertDocuments(itemExtDto.getTreeDtoList());
        return BaseResult.success();
    }

    @Override
    public BaseResult updateRuneLib() {
        LogUtil.info("Start to update the data of runes.");
        mongoDao.insertDocuments(stateDao.readRunes());
        return BaseResult.success();
    }

    @Override
    public BaseResult updateSummonerSpellLib() {
        LogUtil.info("Start to update the data of summoner spells.");
        mongoDao.insertDocuments(stateDao.readSummonerSpells());
        return BaseResult.success();
    }

    @Override
    public BaseResult updateMapLib() {
        LogUtil.info("Start to update the data of maps.");
        mongoDao.insertDocuments(stateDao.readMaps());
        return BaseResult.success();
    }

    @Autowired
    public void setStateDao(StateDao stateDao) {
        this.stateDao = stateDao;
    }

    @Autowired
    public void setMongoDao(MongoDao mongoDao) {
        this.mongoDao = mongoDao;
    }
}
