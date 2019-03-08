package wsg.lol.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import wsg.lol.common.base.Page;
import wsg.lol.dmo.summoner.SummonerDmo;

import java.util.List;

@Mapper
public interface SummonerMapper {

    SummonerDmo selectByPrimaryKey(String id);

    int batchInsertSummoner(List<SummonerDmo> summonerDmoList);

    List<SummonerDmo> queryLastUncheckedSummoners(Page page);

    int batchInsertSummonerIfNotExist(List<SummonerDmo> summonerDmoList);

    List<String> checkSummonersNotExist(List<String> summonerIdList);
}