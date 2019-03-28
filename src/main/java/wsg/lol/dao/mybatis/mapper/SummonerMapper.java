package wsg.lol.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.pojo.base.Page;
import wsg.lol.pojo.dmo.summoner.SummonerDmo;
import wsg.lol.pojo.dto.api.summoner.SummonerDto;
import wsg.lol.pojo.dto.query.GetSummonerDto;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface SummonerMapper {

    SummonerDmo selectByPrimaryKey(String id);

    List<SummonerDmo> queryLastUncheckedSummoners(Page page);

    List<String> checkSummonersNotExist(List<String> summonerIdList);

    SummonerDmo selectByCond(GetSummonerDto getSummonerDto);

    int insertSummoner(SummonerDto summonerDto);

    int updateLastCheckedTimeById(String summonerId, Date now);
}