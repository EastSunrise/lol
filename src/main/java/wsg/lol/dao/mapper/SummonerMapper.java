package wsg.lol.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import wsg.lol.pojo.base.Page;
import wsg.lol.pojo.dmo.summoner.SummonerDmo;
import wsg.lol.pojo.dto.query.GetSummonerDto;

import java.util.List;

@Mapper
public interface SummonerMapper {

    SummonerDmo selectByPrimaryKey(String id);

    int batchInsertSummoner(List<SummonerDmo> summonerDmoList);

    List<SummonerDmo> queryLastUncheckedSummoners(Page page);

    List<String> checkSummonersNotExist(List<String> summonerIdList);

    SummonerDmo selectByCond(GetSummonerDto getSummonerDto);

    int insertSummoner(SummonerDmo summonerDmo);
}