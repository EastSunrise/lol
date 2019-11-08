package wsg.lol.service.main.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wsg.lol.common.pojo.base.BaseResult;
import wsg.lol.common.pojo.dto.query.state.GetChampionDto;
import wsg.lol.common.pojo.dto.query.state.GetChampionListDto;
import wsg.lol.common.pojo.dto.state.ChampionDto;
import wsg.lol.dao.mongo.intf.MongoDao;
import wsg.lol.service.main.intf.ChampionService;

import java.util.List;

/**
 * wsg
 *
 * @author EastSunrise
 */
@Service("championService")
public class ChampionServiceImpl implements ChampionService {

    private MongoDao mongoDao;

    @Override
    public List<ChampionDto> getChampionList(GetChampionListDto getChampionListDto) {
        return mongoDao.getCollectionsByCond(getChampionListDto, ChampionDto.class);
    }

    @Override
    public ChampionDto getChampionInfo(GetChampionDto getChampionDto) {
        if (getChampionDto.getChampionId() != null) {
            return mongoDao.getCollectionById(getChampionDto.getChampionId(), ChampionDto.class);
        } else {
            return mongoDao.getCollectionByCond(getChampionDto, ChampionDto.class);
        }
    }

    @Override
    public BaseResult updateChampions(List<ChampionDto> championDtoList) {
        // TODO: (wangsigen, 2019/11/8) update champions and extra info
        return BaseResult.success();
    }

    @Autowired
    public void setMongoDao(MongoDao mongoDao) {
        this.mongoDao = mongoDao;
    }
}
