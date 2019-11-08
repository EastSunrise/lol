package wsg.lol.service.version.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wsg.lol.common.constant.ConfigConst;
import wsg.lol.common.pojo.base.AppException;
import wsg.lol.common.pojo.base.BaseResult;
import wsg.lol.common.pojo.dto.state.ChampionDto;
import wsg.lol.common.pojo.dto.state.ItemDto;
import wsg.lol.common.pojo.dto.state.item.GroupDto;
import wsg.lol.common.pojo.dto.state.item.ItemExtDto;
import wsg.lol.common.pojo.dto.state.item.TreeDto;
import wsg.lol.common.pojo.dto.state.others.MapDto;
import wsg.lol.common.pojo.dto.state.rune.RuneTreeDto;
import wsg.lol.common.pojo.dto.state.spell.SummonerSpellDto;
import wsg.lol.common.result.version.VersionResult;
import wsg.lol.common.util.AssertUtils;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.dao.data.intf.DataDao;
import wsg.lol.dao.data.intf.GeneralDao;
import wsg.lol.dao.mongo.intf.MongoDao;
import wsg.lol.dao.mybatis.mapper.ConfigMapper;
import wsg.lol.service.main.intf.ChampionService;
import wsg.lol.service.version.intf.VersionService;

import java.util.List;

/**
 * wsg
 *
 * @author EastSunrise
 */
@Service("versionAction")
public class VersionServiceImpl implements VersionService {

    private static Logger logger = LoggerFactory.getLogger(VersionService.class);

    private DataDao dataDao;

    private MongoDao mongoDao;

    private GeneralDao generalDao;

    private ConfigMapper configMapper;

    private ChampionService championService;

    @Override
    public VersionResult getVersion() {
        VersionResult versionResult = new VersionResult();
        versionResult.setCurrentVersion(configMapper.getConfigValue(ConfigConst.CONFIG_NAME_CURRENT_VERSION));
        versionResult.setLatestVersion(generalDao.getLatestVersion());
        return versionResult;
    }

    @Override
    @Transactional
    public BaseResult updateVersion(String version) {
        AssertUtils.isSuccess(this.updateChampionLib(version));
        AssertUtils.isSuccess(this.updateItemLib(version));
        AssertUtils.isSuccess(this.updateMapLib(version));
        AssertUtils.isSuccess(this.updateRuneLib(version));
        AssertUtils.isSuccess(this.updateSummonerSpellLib(version));

        int count = configMapper.updateConfig(ConfigConst.CONFIG_NAME_CURRENT_VERSION, version);
        if (1 != count) {
            throw new AppException("Failed to update the config of current version.");
        }
        return ResultUtils.success();
    }

    @Override
    public BaseResult updateChampionLib(String version) {
        logger.info("Start to update the data of champions.");
        List<ChampionDto> championDtoList = dataDao.readChampions(version);
        return championService.updateChampions(championDtoList);
    }

    @Override
    public BaseResult updateItemLib(String version) {
        logger.info("Start to update the data of items.");
        mongoDao.dropCollection(ItemDto.class);
        mongoDao.dropCollection(GroupDto.class);
        mongoDao.dropCollection(TreeDto.class);
        ItemExtDto itemExtDto = dataDao.readItems(version);
        mongoDao.insertDocuments(itemExtDto.getItemDtoList());
        mongoDao.insertDocuments(itemExtDto.getGroupDtoList());
        mongoDao.insertDocuments(itemExtDto.getTreeDtoList());
        return ResultUtils.success();
    }

    @Override
    public BaseResult updateRuneLib(String version) {
        logger.info("Start to update the data of runes.");
        mongoDao.dropCollection(RuneTreeDto.class);
        mongoDao.insertDocuments(dataDao.readRunes(version));
        return ResultUtils.success();
    }

    @Override
    public BaseResult updateSummonerSpellLib(String version) {
        logger.info("Start to update the data of summoner spells.");
        mongoDao.dropCollection(SummonerSpellDto.class);
        mongoDao.insertDocuments(dataDao.readSummonerSpells(version));
        return ResultUtils.success();
    }

    @Override
    public BaseResult updateMapLib(String version) {
        logger.info("Start to update the data of maps.");
        mongoDao.dropCollection(MapDto.class);
        mongoDao.insertDocuments(dataDao.readMaps(version));
        return ResultUtils.success();
    }

    @Autowired
    public void setGeneralDao(GeneralDao generalDao) {
        this.generalDao = generalDao;
    }

    @Autowired
    public void setDataDao(DataDao dataDao) {
        this.dataDao = dataDao;
    }

    @Autowired
    public void setMongoDao(MongoDao mongoDao) {
        this.mongoDao = mongoDao;
    }

    @Autowired
    public void setConfigMapper(ConfigMapper configMapper) {
        this.configMapper = configMapper;
    }
}
