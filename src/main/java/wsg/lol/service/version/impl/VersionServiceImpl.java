package wsg.lol.service.version.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wsg.lol.common.utils.LogUtil;
import wsg.lol.dao.mongo.intf.MongoDao;
import wsg.lol.dao.state.intf.StateDao;
import wsg.lol.pojo.base.BaseResult;
import wsg.lol.pojo.dto.state.champion.ChampionDto;
import wsg.lol.pojo.dto.state.item.GroupDto;
import wsg.lol.pojo.dto.state.item.ItemDto;
import wsg.lol.pojo.dto.state.item.ItemExtDto;
import wsg.lol.pojo.dto.state.item.TreeDto;
import wsg.lol.pojo.dto.state.others.MapDto;
import wsg.lol.pojo.dto.state.others.VersionDto;
import wsg.lol.pojo.dto.state.rune.RuneTreeDto;
import wsg.lol.pojo.dto.state.spell.SummonerSpellDto;
import wsg.lol.pojo.result.VersionResult;
import wsg.lol.service.version.intf.VersionService;

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
    public VersionResult getVersion() {
        VersionResult versionResult = new VersionResult();
        List<VersionDto> versionDtoList = mongoDao.getCollections(VersionDto.class);
        if (!versionDtoList.isEmpty()) {
            versionResult.setCurrentVersion(versionDtoList.get(0).getVersion());
        }
        versionResult.setLatestVersion(stateDao.readLatestVersion());
        return versionResult;
    }

    @Override
    public BaseResult updateVersion(String version) {
        BaseResult baseResult = updateChampionLib(version);
        if (!baseResult.isSuccess()) {
            return baseResult;
        }
        baseResult = updateItemLib(version);
        if (!baseResult.isSuccess()) {
            return baseResult;
        }
        baseResult = updateMapLib(version);
        if (!baseResult.isSuccess()) {
            return baseResult;
        }
        baseResult = updateRuneLib(version);
        if (!baseResult.isSuccess()) {
            return baseResult;
        }
        baseResult = updateSummonerSpellLib(version);
        if (!baseResult.isSuccess()) {
            return baseResult;
        }
        mongoDao.dropCollection(VersionDto.class);
        VersionDto versionDto = new VersionDto();
        versionDto.setVersion(version);
        mongoDao.insertDocument(versionDto);
        return BaseResult.success();
    }

    @Override
    public BaseResult updateChampionLib(String version) {
        LogUtil.info("Start to update the data of champions.");
        mongoDao.dropCollection(ChampionDto.class);
        mongoDao.insertDocuments(stateDao.readChampions(version));
        return BaseResult.success();
    }

    @Override
    public BaseResult updateItemLib(String version) {
        LogUtil.info("Start to update the data of items.");
        mongoDao.dropCollection(ItemDto.class);
        mongoDao.dropCollection(GroupDto.class);
        mongoDao.dropCollection(TreeDto.class);
        ItemExtDto itemExtDto = stateDao.readItems(version);
        mongoDao.insertDocuments(itemExtDto.getItemDtoList());
        mongoDao.insertDocuments(itemExtDto.getGroupDtoList());
        mongoDao.insertDocuments(itemExtDto.getTreeDtoList());
        return BaseResult.success();
    }

    @Override
    public BaseResult updateRuneLib(String version) {
        LogUtil.info("Start to update the data of runes.");
        mongoDao.dropCollection(RuneTreeDto.class);
        mongoDao.insertDocuments(stateDao.readRunes(version));
        return BaseResult.success();
    }

    @Override
    public BaseResult updateSummonerSpellLib(String version) {
        LogUtil.info("Start to update the data of summoner spells.");
        mongoDao.dropCollection(SummonerSpellDto.class);
        mongoDao.insertDocuments(stateDao.readSummonerSpells(version));
        return BaseResult.success();
    }

    @Override
    public BaseResult updateMapLib(String version) {
        LogUtil.info("Start to update the data of maps.");
        mongoDao.dropCollection(MapDto.class);
        mongoDao.insertDocuments(stateDao.readMaps(version));
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
