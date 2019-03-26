package wsg.lol.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wsg.lol.dao.mongo.intf.MongoDao;
import wsg.lol.pojo.dto.query.state.GetChampionDto;
import wsg.lol.pojo.dto.query.state.GetChampionListDto;
import wsg.lol.pojo.dto.state.champion.ChampionDto;
import wsg.lol.service.user.intf.ChampionService;

import java.util.List;

/**
 * wsg
 *
 * @author wangsigen
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

    @Autowired
    public void setMongoDao(MongoDao mongoDao) {
        this.mongoDao = mongoDao;
    }
}
