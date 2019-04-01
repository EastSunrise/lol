package wsg.lol.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.dao.mybatis.common.MyMapper;
import wsg.lol.pojo.base.Page;
import wsg.lol.pojo.dmo.summoner.SummonerDmo;
import wsg.lol.pojo.dto.api.summoner.SummonerDto;
import wsg.lol.pojo.dto.query.GetSummonerDto;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface SummonerMapper extends MyMapper<SummonerDmo> {

    int insertSummoner(SummonerDto summonerDto);

    int updateLastCheckedTimeById(String summonerId, Date now);

    SummonerDmo selectByPrimaryKey(String id);

    List<SummonerDmo> selectLastUncheckedSummoners(Page page);

    List<String> selectSummonersNotExist(List<String> summonerIdList);

    SummonerDmo selectByCond(GetSummonerDto getSummonerDto);
}