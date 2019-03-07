package wsg.lol.dao;

import org.apache.ibatis.annotations.Mapper;
import wsg.lol.common.base.Page;
import wsg.lol.dmo.Summoner;
import wsg.lol.dmo.summoner.SummonerDmo;

import java.util.List;

@Mapper
public interface SummonerMapper {
    int deleteByPrimaryKey(String id);

    int insert(Summoner record);

    int insertSelective(Summoner record);

    Summoner selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Summoner record);

    int updateByPrimaryKey(Summoner record);

    int batchInsertSummoner(List<SummonerDmo> summonerDmoList);

    List<SummonerDmo> queryLastUncheckedSummoners(Page page);

    int batchInsertSummonerIfNotExist(List<SummonerDmo> summonerDmoList);
}